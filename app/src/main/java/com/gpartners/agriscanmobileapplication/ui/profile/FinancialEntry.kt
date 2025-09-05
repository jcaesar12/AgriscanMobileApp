package com.gpartners.agriscanmobileapplication.ui.profile

data class FinancialEntry(
    val type: String,         // "Income" or "Expense"
    val category: String,
    val amount: Double,
    val date: String          // "yyyy-MM-dd"
)
