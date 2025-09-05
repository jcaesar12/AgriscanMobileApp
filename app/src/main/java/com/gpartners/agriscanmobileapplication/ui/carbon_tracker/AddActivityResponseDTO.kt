package com.gpartners.agriscanmobileapplication.ui.carbon_tracker

data class AddActivityResponseDTO(
    val id: Int,
    val activityName: String,
    val carbonAmount: Double,
    val activityDate: String   // formatted "YYYY-MM-DD"
)