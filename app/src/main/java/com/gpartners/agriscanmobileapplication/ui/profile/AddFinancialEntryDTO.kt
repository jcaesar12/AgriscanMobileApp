package com.gpartners.agriscanmobileapplication.ui.profile

import java.time.LocalDate

data class AddFinancialEntryDTO(
    val entryType: String,
    val category: String,
    val amount: Double,
    val entryDate: LocalDate? = null,
    val notes: String? = null
)