package com.gpartners.agriscanmobileapplication.ui.carbon_tracker

import java.text.SimpleDateFormat
import java.util.*

/**
 * Utility object for date handling in Carbon Tracker
 */
object DateUtils {

    private const val DATE_FORMAT = "yyyy-MM-dd"

    /**
     * Returns the current date as a string in yyyy-MM-dd format
     */
    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale.US)
        return sdf.format(Date())
    }

    /**
     * Parses a date string into a Date object.
     */
    fun parseDate(dateString: String): Date? {
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale.US)
        return try {
            sdf.parse(dateString)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Compares two date strings.
     * Returns:
     * 0 if equal,
     * <0 if firstDate < secondDate,
     * >0 if firstDate > secondDate
     */
    fun compareDates(firstDate: String, secondDate: String): Int {
        val date1 = parseDate(firstDate)
        val date2 = parseDate(secondDate)
        if (date1 != null && date2 != null) {
            return date1.compareTo(date2)
        }
        return 0
    }
}
