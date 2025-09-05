package com.gpartners.agriscanmobileapplication.ui.carbon_tracker

data class PastActivityDTO(
    val activityName: String,
    val activityDate: String,   // formatted "YYYY-MM-DD"
    val carbonAmount: Double,
    val deficit: Double,
    val dotColor: String
)