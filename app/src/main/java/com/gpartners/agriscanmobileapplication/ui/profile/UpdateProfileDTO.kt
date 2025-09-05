package com.gpartners.agriscanmobileapplication.ui.profile

data class UpdateProfileDTO(
    val firstName: String? = null,
    val middleName: String? = null,
    val surname: String? = null,
    val sexId: Int? = null,
    val villageId: Int? = null,
    val nationalId: String? = null
)