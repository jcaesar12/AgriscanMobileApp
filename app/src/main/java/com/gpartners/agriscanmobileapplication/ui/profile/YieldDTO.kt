package com.gpartners.agriscanmobileapplication.ui.profile

import java.time.LocalDateTime

data class YieldDTO(
    val cropId: Int,
    val quantity: Double,
    val date: LocalDateTime
)