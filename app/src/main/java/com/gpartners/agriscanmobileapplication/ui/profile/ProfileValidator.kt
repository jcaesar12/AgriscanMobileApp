package com.gpartners.agriscanmobileapplication.ui.profile

object ProfileValidator {

    /** Validate profile fields */
    fun validateProfile(
        fullName: String,
        phone: String,
        gender: String,
        county: String,
        subCounty: String
    ): Boolean {
        if (fullName.isBlank() || phone.isBlank() || gender.isBlank() ||
            county.isBlank() || subCounty.isBlank()
        ) return false

        // Simple phone check
        if (!phone.matches(Regex("^\\+?[0-9]{10,13}\$"))) return false

        return true
    }

    /** Validate password change fields */
    fun validatePassword(
        current: String,
        newPassword: String,
        confirmPassword: String
    ): Boolean {
        if (current.isBlank() || newPassword.isBlank() || confirmPassword.isBlank()) return false
        if (newPassword != confirmPassword) return false
        if (newPassword.length < 6) return false
        return true
    }
}