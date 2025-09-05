package com.gpartners.agriscanmobileapplication.ui.carbon_tracker

import java.time.LocalDate

data class AddActivityDTO(
    val activityId: Int,
    val activityDate: LocalDate,
    val notes: String? = ""
)