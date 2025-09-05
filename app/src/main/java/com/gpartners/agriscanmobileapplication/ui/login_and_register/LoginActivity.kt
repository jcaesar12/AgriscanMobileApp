package com.gpartners.agriscanmobileapplication.ui.login_and_register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.gpartners.agriscanmobileapplication.R
import com.gpartners.agriscanmobileapplication.ui.dashboard.DashboardActivity
import com.gpartners.agriscanmobileapplication.ui.FarmerRegistration
import com.gpartners.agriscanmobileapplication.ui.db.AppDatabase
import com.gpartners.agriscanmobileapplication.ui.retrofitinstance.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerText: TextView
    private lateinit var forgotPasswordText: TextView
    private lateinit var rememberMeCheckbox: CheckBox
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize DB
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "agriscan-db"
        ).allowMainThreadQueries().build()

        // UI elements
        usernameEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.buttonLogin)
        registerText = findViewById(R.id.textViewRegister)
        forgotPasswordText = findViewById(R.id.textViewForgotPassword)
        rememberMeCheckbox = findViewById(R.id.checkboxRememberMe)

        // Login button
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                if (isOnline()) {
                    loginFarmerOnline(username, password)
                } else {
                    loginFarmerOffline(username, password)
                }
            }
        }
    }

    // Online login with Retrofit
    private fun loginFarmerOnline(username: String, password: String) {
        val loginData = FarmerLoginDTO(username, password)

        RetrofitInstance.farmerLoginApi.loginFarmer(loginData)
            .enqueue(object : Callback<FarmerLoginResponseDTO> {
                override fun onResponse(
                    call: Call<FarmerLoginResponseDTO>,
                    response: Response<FarmerLoginResponseDTO>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null && body.success) {
                            Toast.makeText(
                                this@LoginActivity,
                                "Welcome ${body.full_name}",
                                Toast.LENGTH_SHORT
                            ).show()

                            // Save to SQLite for offline login
                            val farmer = FarmerRegistration(
                                id = body.farmer_id,
                                username = username,
                                password = password, // Consider hashing
                                firstName = body.full_name.split(" ").firstOrNull(),
                                surname = body.full_name.split(" ").lastOrNull()
                            )
                            db.farmerRegistrationDao().insertFarmer(farmer)

                            navigateToDashboard(body.farmer_id, body.full_name)
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                body?.message ?: "Login failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Server error: ${response.code()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<FarmerLoginResponseDTO>, t: Throwable) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Network error: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    // Offline login with SQLite
    private fun loginFarmerOffline(username: String, password: String) {
        val farmer = db.farmerRegistrationDao().login(username, password)
        if (farmer != null) {
            Toast.makeText(this, "Welcome back ${farmer.firstName ?: farmer.username}", Toast.LENGTH_SHORT).show()
            navigateToDashboard(farmer.id ?: 0, farmer.firstName ?: farmer.username)
        } else {
            Toast.makeText(this, "Offline login failed. Please check credentials.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToDashboard(farmerId: Int, fullName: String) {
        val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
        intent.putExtra("farmer_id", farmerId)
        intent.putExtra("full_name", fullName)
        startActivity(intent)
        finish()
    }

    private fun isOnline(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo?.isConnectedOrConnecting == true
    }
}
