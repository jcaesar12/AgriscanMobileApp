package com.gpartners.agriscanmobileapplication.ui.profile

import java.time.LocalDate

data class FinancialEntryDTO(
    val category: String,
    val entryDate: LocalDate,
    val amount: Double,
    val entryType: String
)