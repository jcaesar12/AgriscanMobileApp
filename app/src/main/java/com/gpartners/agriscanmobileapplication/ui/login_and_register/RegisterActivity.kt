package com.gpartners.agriscanmobileapplication.ui.login_and_register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.gpartners.agriscanmobileapplication.R
import com.gpartners.agriscanmobileapplication.ui.mock_data.MockDataProvider
import com.gpartners.agriscanmobileapplication.ui.FarmerRegistration
import com.gpartners.agriscanmobileapplication.ui.retrofitinstance.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var fullNameEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var genderSpinner: Spinner
    private lateinit var countySpinner: Spinner
    private lateinit var subCountySpinner: Spinner
    private lateinit var farmTypeSpinner: Spinner
    private lateinit var signUpButton: Button
    private lateinit var loginText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Bind views
        fullNameEditText = findViewById(R.id.editTextFullName)
        usernameEditText = findViewById(R.id.editTextUsername)
        passwordEditText = findViewById(R.id.editTextPassword)
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber)
        genderSpinner = findViewById(R.id.spinnerGender)
        countySpinner = findViewById(R.id.spinnerCounty)
        subCountySpinner = findViewById(R.id.spinnerSubCounty)
        farmTypeSpinner = findViewById(R.id.spinnerFarmType)
        signUpButton = findViewById(R.id.buttonSignUp)
        loginText = findViewById(R.id.textViewLogin)

        setupSpinners()

        // Sign Up click
        signUpButton.setOnClickListener {
            if (validateForm()) {
                registerFarmer()
            }
        }

        // Navigate to login
        loginText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun setupSpinners() {
        // Gender
        genderSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            MockDataProvider.genders
        )

        // County
        countySpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            MockDataProvider.counties
        )

        // SubCounty depends on county
        countySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val selectedCounty = MockDataProvider.counties[position]
                val subCounties = MockDataProvider.subCounties[selectedCounty]
                    ?: listOf("Select county first")
                subCountySpinner.adapter = ArrayAdapter(
                    this@RegisterActivity,
                    android.R.layout.simple_spinner_dropdown_item,
                    subCounties
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Farm Types
        farmTypeSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            MockDataProvider.farmTypes
        )
    }

    private fun validateForm(): Boolean {
        val fullName = fullNameEditText.text.toString().trim()
        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val phone = phoneNumberEditText.text.toString().trim()
        val gender = genderSpinner.selectedItem.toString()
        val county = countySpinner.selectedItem.toString()
        val subCounty = subCountySpinner.selectedItem.toString()
        val farmType = farmTypeSpinner.selectedItem.toString()

        return when {
            fullName.isEmpty() -> { showToast("Enter full name"); false }
            username.isEmpty() -> { showToast("Enter username"); false }
            password.isEmpty() -> { showToast("Enter password"); false }
            phone.isEmpty() -> { showToast("Enter phone number"); false }
            gender == "Select gender" -> { showToast("Select gender"); false }
            county == "Select county" -> { showToast("Select county"); false }
            subCounty == "Select subcounty" || subCounty == "Select county first" -> { showToast("Select subcounty"); false }
            farmType == "Select your main farming activity" -> { showToast("Select farm type"); false }
            else -> true
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun registerFarmer() {
        val fullName = fullNameEditText.text.toString().trim()
        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val gender = genderSpinner.selectedItem.toString()
        val county = countySpinner.selectedItem.toString()
        val subCounty = subCountySpinner.selectedItem.toString()
        val farmType = farmTypeSpinner.selectedItem.toString()

        // Split full name
        val nameParts = fullName.split(" ")
        val firstName = nameParts.getOrNull(0) ?: ""
        val middleName = nameParts.getOrNull(1)
        val surname = nameParts.drop(2).joinToString(" ").ifEmpty { null }

        // Construct DTO for backend
        // Example: create the FarmerRegistrationDTO to match your full DTO
        val farmerDTO = FarmerRegistrationDTO(
            first_name = firstName,
            middle_name = middleName,
            surname = surname ?: "",
            username = username,
            password = password,
            gender = gender, // maps to SexId in backend
            dob = selectedDob, // must be in ISO format "YYYY-MM-DD"
            village_id = selectedVillageId, // fetch from spinner selection
            marital_status = selectedMaritalStatus,
            education_level = selectedEducationLevel,
            household_size = householdSize?.toIntOrNull(),
            disability_status = selectedDisabilityStatus,
            primary_occupation = farmType,
            other_primary_occupation = otherPrimaryOccupation,
            secondary_occupation = selectedSecondaryOccupation,
            other_secondary_occupation = otherSecondaryOccupation,
            annual_income_bracket = selectedIncomeBracket,
            credit_access = if (hasCreditAccess) 1 else 0,
            members_cooperative = if (isMemberCoop) 1 else 0,
            farmer_cooperative = selectedCooperative,
            insurance_access = selectedInsuranceAccess,
            national_id = nationalId,
            data_sharing_consent = consentGiven
        )


        // Call backend API
        RetrofitInstance.farmerApi.registerFarmer(farmerDTO)
            .enqueue(object : Callback<FarmerResponseDTO> {
                override fun onResponse(
                    call: Call<FarmerResponseDTO>,
                    response: Response<FarmerResponseDTO>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val farmer = response.body()!!

                        showToast("Registration successful!")

                        // Save locally for offline login
                        saveFarmerLocally(farmer, password)

                        // Navigate to login
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        finish()
                    } else {
                        showToast("Registration failed: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<FarmerResponseDTO>, t: Throwable) {
                    showToast("Network error: ${t.message}")
                }
            })
    }

    private fun saveFarmerLocally(farmer: FarmerResponseDTO, plainPassword: String) {
        // Save in SQLite using Room
        lifecycleScope.launch(Dispatchers.IO) {
            val db = AppDatabase.getInstance(this@RegisterActivity)
            val localFarmer = FarmerRegistration(
                firstName = farmer.full_name.split(" ").getOrNull(0) ?: "",
                middlename = farmer.full_name.split(" ").getOrNull(1),
                surname = farmer.full_name.split(" ").drop(2).joinToString(" ").ifEmpty { null },
                username = farmer.username,
                password = plainPassword, // store encrypted if needed
                villageId = farmer.village_id,
                sexId = farmer.sex_id,
                dob = farmer.dob
            )
            db.farmerDao().insertFarmer(localFarmer)
        }
    }
}
