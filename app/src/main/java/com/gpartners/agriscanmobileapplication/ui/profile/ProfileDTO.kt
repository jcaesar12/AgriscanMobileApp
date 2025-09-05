package com.gpartners.agriscanmobileapplication.ui.profile

data class ProfileDTO(
    val fullName: String,
    val idNumber: String,
    val gender: String? = null,
    val county: String? = null,
    val subCounty: String? = null,
    val username: String
)
