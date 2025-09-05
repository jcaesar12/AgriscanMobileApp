package com.gpartners.agriscanmobileapplication.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gpartners.agriscanmobileapplication.R
import com.gpartners.agriscanmobileapplication.ui.profile.FinancialEntry
import com.gpartners.agriscanmobileapplication.ui.profile.UserProfile
import com.gpartners.agriscanmobileapplication.ui.profile.DateUtils
import com.gpartners.agriscanmobileapplication.ui.profile.FinancialCalculator
import com.gpartners.agriscanmobileapplication.ui.profile.ProfileValidator

class ProfileActivity : AppCompatActivity() {


    private lateinit var tabProfile: TextView
    private lateinit var tabFinances: TextView
    private lateinit var tabYields: TextView

    private lateinit var profileContainer: LinearLayout

    private var currentUser = UserProfile(
        fullName = "John Doe",
        phoneNumber = "+254712345678",
        gender = "Male",
        county = "Nairobi",
        subCounty = "Westlands"
    )

    // Financial entries
    private var financialEntries = mutableListOf<FinancialEntry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Bind tabs and container
        tabProfile = findViewById(R.id.tabProfile)
        tabFinances = findViewById(R.id.tabFinances)
        tabYields = findViewById(R.id.tabYields)
        profileContainer = findViewById(R.id.profileContainer)

        // Default tab
        showProfileTab()

        // Tab click listeners
        tabProfile.setOnClickListener { showProfileTab() }
        tabFinances.setOnClickListener { showFinancesTab() }
        tabYields.setOnClickListener { showYieldsTab() }
    }

    /** Highlight tab */
    private fun highlightTab(selected: TextView) {
        listOf(tabProfile, tabFinances, tabYields).forEach { tab ->
            if (tab == selected) {
                tab.setBackgroundResource(R.drawable.tab_active_bg)
                tab.setTextColor(resources.getColor(R.color.green, theme))
            } else {
                tab.setBackgroundResource(R.drawable.tab_inactive_bg)
                tab.setTextColor(resources.getColor(R.color.grey, theme))
            }
        }
    }

    /** -------------------- PROFILE TAB -------------------- */
    private fun showProfileTab() {
        highlightTab(tabProfile)
        profileContainer.removeAllViews()

        val profileView = LayoutInflater.from(this)
            .inflate(R.layout.layout_profile_info, profileContainer, false)

        val inputFullName = profileView.findViewById<EditText>(R.id.inputFullName)
        val inputPhone = profileView.findViewById<EditText>(R.id.inputPhoneNumber)
        val inputGender = profileView.findViewById<EditText>(R.id.inputGender)
        val inputCounty = profileView.findViewById<EditText>(R.id.inputCounty)
        val inputSubCounty = profileView.findViewById<EditText>(R.id.inputSubCounty)
        val btnEdit = profileView.findViewById<Button>(R.id.btnEditProfile)
        val btnSave = profileView.findViewById<Button>(R.id.btnSaveChanges)

        // Pre-fill
        inputFullName.setText(currentUser.fullName)
        inputPhone.setText(currentUser.phoneNumber)
        inputGender.setText(currentUser.gender)
        inputCounty.setText(currentUser.county)
        inputSubCounty.setText(currentUser.subCounty)

        btnEdit.setOnClickListener {
            listOf(inputFullName, inputPhone, inputGender, inputCounty, inputSubCounty)
                .forEach { it.isEnabled = true }
            btnSave.visibility = View.VISIBLE
        }

        btnSave.setOnClickListener {
            val fullName = inputFullName.text.toString()
            val phone = inputPhone.text.toString()
            val gender = inputGender.text.toString()
            val county = inputCounty.text.toString()
            val subCounty = inputSubCounty.text.toString()

            if (ProfileValidator.validateProfile(fullName, phone, gender, county, subCounty)) {
                currentUser.apply {
                    this.fullName = fullName
                    this.phoneNumber = phone
                    this.gender = gender
                    this.county = county
                    this.subCounty = subCounty
                }
                Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show()
                btnSave.visibility = View.GONE
                listOf(inputFullName, inputPhone, inputGender, inputCounty, inputSubCounty)
                    .forEach { it.isEnabled = false }
            } else {
                Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
            }
        }

        profileContainer.addView(profileView)
        showChangePasswordForm()
    }

    /** Change Password form */
    private fun showChangePasswordForm() {
        val passwordView = LayoutInflater.from(this)
            .inflate(R.layout.layout_change_password, profileContainer, false)

        val inputCurrent = passwordView.findViewById<EditText>(R.id.inputCurrentPassword)
        val inputNew = passwordView.findViewById<EditText>(R.id.inputNewPassword)
        val inputConfirm = passwordView.findViewById<EditText>(R.id.inputConfirmPassword)
        val btnChange = passwordView.findViewById<Button>(R.id.btnChangePassword)
        val btnLogout = passwordView.findViewById<Button>(R.id.btnLogout)

        btnChange.setOnClickListener {
            val current = inputCurrent.text.toString()
            val newPass = inputNew.text.toString()
            val confirm = inputConfirm.text.toString()
            if (ProfileValidator.validatePassword(current, newPass, confirm)) {
                Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show()
                listOf(inputCurrent, inputNew, inputConfirm).forEach { it.text.clear() }
            } else {
                Toast.makeText(this, "Invalid password or mismatch", Toast.LENGTH_SHORT).show()
            }
        }

        btnLogout.setOnClickListener {
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
        }

        profileContainer.addView(passwordView)
    }

    /** -------------------- FINANCES TAB -------------------- */
    private fun showFinancesTab() {
        highlightTab(tabFinances)
        profileContainer.removeAllViews()

        // Always add overview
        val overviewView = LayoutInflater.from(this)
            .inflate(R.layout.layout_finances_overview, profileContainer, false)

        val incomeCard = overviewView.findViewById<TextView>(R.id.textIncomeValue)
        val expenseCard = overviewView.findViewById<TextView>(R.id.textExpenseValue)
        val balanceCard = overviewView.findViewById<TextView>(R.id.textBalanceValue)

        incomeCard.text =
            "+KES ${String.format("%,.2f", FinancialCalculator.getTotalIncome(financialEntries))}"
        expenseCard.text =
            "-KES ${String.format("%,.2f", FinancialCalculator.getTotalExpenses(financialEntries))}"
        balanceCard.text =
            "KES ${String.format("%,.2f", FinancialCalculator.getCurrentBalance(financialEntries))}"

        profileContainer.addView(overviewView)

        // Then decide: empty vs list
        if (financialEntries.isEmpty()) {
            val emptyView = LayoutInflater.from(this)
                .inflate(R.layout.layout_finances_empty, profileContainer, false)

            val btnAdd = emptyView.findViewById<Button>(R.id.btnAddActivityHeader)
            btnAdd.setOnClickListener { showAddFinancialEntryForm() }

            profileContainer.addView(emptyView)

        } else {
            val listView = LayoutInflater.from(this)
                .inflate(R.layout.layout_finances_list, profileContainer, false)

            val recycler = listView.findViewById<RecyclerView>(R.id.rvFinances)
            recycler.layoutManager = LinearLayoutManager(this)
            recycler.adapter = FinancialEntryAdapter(financialEntries)

            val btnAdd = listView.findViewById<Button>(R.id.btnAddActivityHeader)
            btnAdd.setOnClickListener { showAddFinancialEntryForm() }

            profileContainer.addView(listView)
        }
    }

    /** Add Financial Entry Form */
    private fun showAddFinancialEntryForm() {
        profileContainer.removeAllViews()

        // Always add overview first
        val overviewView = LayoutInflater.from(this)
            .inflate(R.layout.layout_finances_overview, profileContainer, false)

        val incomeCard = overviewView.findViewById<TextView>(R.id.textIncomeValue)
        val expenseCard = overviewView.findViewById<TextView>(R.id.textExpenseValue)
        val balanceCard = overviewView.findViewById<TextView>(R.id.textBalanceValue)

        incomeCard.text =
            "+KES ${String.format("%,.2f", FinancialCalculator.getTotalIncome(financialEntries))}"
        expenseCard.text =
            "-KES ${String.format("%,.2f", FinancialCalculator.getTotalExpenses(financialEntries))}"
        balanceCard.text =
            "KES ${String.format("%,.2f", FinancialCalculator.getCurrentBalance(financialEntries))}"

        profileContainer.addView(overviewView)

        // Then add the add-entry form
        val addView = LayoutInflater.from(this)
            .inflate(R.layout.layout_finances_add, profileContainer, false)

        val spinnerType = addView.findViewById<Spinner>(R.id.spinnerType)
        val inputAmount = addView.findViewById<EditText>(R.id.inputAmount)
        val spinnerCategory = addView.findViewById<Spinner>(R.id.spinnerActivity)
        val inputDate = addView.findViewById<EditText>(R.id.inputDate)
        val btnSubmit = addView.findViewById<Button>(R.id.btnConfirmAddActivity)
        val btnCancel = addView.findViewById<Button>(R.id.btnCancel)

        // Type spinner (Income/Expense)
        spinnerType.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listOf("Income", "Expense")
        )

        // Category spinner (dummy values for now, replace with real categories if needed)
        spinnerCategory.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listOf("General", "Sales", "Farm Inputs", "Transport")
        )

        // Date picker
        DateUtils.attachDatePicker(this, inputDate)

        btnSubmit.setOnClickListener {
            val type = spinnerType.selectedItem.toString()
            val category = spinnerCategory.selectedItem.toString()
            val amount = inputAmount.text.toString().toDoubleOrNull() ?: 0.0
            val date = inputDate.text.toString()

            financialEntries.add(FinancialEntry(type, category, amount, date))
            showFinancesTab() // refresh
        }

        btnCancel.setOnClickListener { showFinancesTab() }

        profileContainer.addView(addView)
    }

    /** -------------------- YIELDS TAB -------------------- */
    private fun showYieldsTab() {
        highlightTab(tabYields)
        profileContainer.removeAllViews()
        val textView = TextView(this)
        textView.text = "Yields tab content will go here"
        textView.setPadding(16,16,16,16)
        profileContainer.addView(textView)
    }
}
