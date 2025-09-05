package com.gpartners.agriscanmobileapplication.ui.carbon_tracker

/**
 * Utility class to calculate estimated carbon impact for a given activity.
 */
object CarbonCalculator {

    /**
     * Returns the estimated carbon impact (kg CO2) for a given activity name.
     * Extend this method to add more activities and accurate values.
     */
    fun calculateImpact(activityName: String): Int {
        return when (activityName) {
            "Cover Cropping" -> 10
            "Solar Panels" -> 25
            "Tree Planting" -> 15
            "Organic Fertilizer" -> 8
            "Water Conservation" -> 5
            else -> 0
        }
    }

    /**
     * Returns a formatted string for display purposes.
     */
    fun formatImpact(impact: Int): String {
        return "$impact kg CO₂"
    }
}
