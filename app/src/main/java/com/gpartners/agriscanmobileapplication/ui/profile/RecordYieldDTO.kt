package com.gpartners.agriscanmobileapplication.ui.profile

import java.time.LocalDate

data class RecordYieldDTO(
    val cropId: Int,
    val quantity: Double,
    val recordDate: LocalDate
)