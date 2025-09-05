package com.gpartners.agriscanmobileapplication.ui.login_and_register

data class FarmerRegistrationDTO(
    val first_name: String,
    val middle_name: String? = null,
    val surname: String,
    val username: String,
    val password: String,
    val gender: String,              // maps to SexId
    val dob: String,                 // use ISO format "YYYY-MM-DD"
    val village_id: Int,
    val marital_status: String,
    val education_level: String,
    val household_size: Int? = null,
    val disability_status: String,
    val primary_occupation: String,
    val other_primary_occupation: String? = null,
    val secondary_occupation: String,
    val other_secondary_occupation: String? = null,
    val annual_income_bracket: String,
    val credit_access: Int? = null,
    val members_cooperative: Int? = null,
    val farmer_cooperative: String,
    val insurance_access: String,
    val national_id: String,
    val data_sharing_consent: Boolean? = false
)