package com.gpartners.agriscanmobileapplication.ui.login_and_register

data class FarmerLoginResponseDTO(
    val success: Boolean,
    val message: String,
    val farmer_id: Int? = null,
    val full_name: String? = null,
    val role: String? = null // e.g., "Admin", "FieldOfficer", "Farmer"
)