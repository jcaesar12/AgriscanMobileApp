package com.gpartners.agriscanmobileapplication.ui.profile

import com.gpartners.agriscanmobileapplication.ui.profile.FinancialEntry

object FinancialCalculator {

    /** Total income from list of entries */
    fun getTotalIncome(entries: List<FinancialEntry>): Double =
        entries.filter { it.type == "Income" }.sumOf { it.amount }

    /** Total expenses from list of entries */
    fun getTotalExpenses(entries: List<FinancialEntry>): Double =
        entries.filter { it.type == "Expense" }.sumOf { it.amount }

    /** Current balance */
    fun getCurrentBalance(entries: List<FinancialEntry>): Double =
        getTotalIncome(entries) - getTotalExpenses(entries)
}