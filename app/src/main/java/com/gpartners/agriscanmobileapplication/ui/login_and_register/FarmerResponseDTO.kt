package com.gpartners.agriscanmobileapplication.ui.login_and_register

data class FarmerResponseDTO(
    val id: Int,
    val full_name: String,
    val username: String,
    val village_id: Int,
    val sex_id: Int,
    val dob: String,                // keep as String (format "YYYY-MM-DD") to match JSON
    val marital_status_id: Int,
    val education_level_id: Int,
    val national_id: String
)