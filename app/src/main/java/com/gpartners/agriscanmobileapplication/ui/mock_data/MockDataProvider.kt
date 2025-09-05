package com.gpartners.agriscanmobileapplication.ui.mock_data

object MockDataProvider {
    val genders = listOf("Select gender", "Male", "Female")

    val counties = listOf("Select county", "Nairobi", "Kiambu", "Machakos")

    val subCounties = mapOf(
        "Nairobi" to listOf("Westlands", "Lang’ata", "Kasarani"),
        "Kiambu" to listOf("Thika", "Ruiru", "Limuru"),
        "Machakos" to listOf("Mavoko", "Kangundo", "Kathiani")
    )

    val farmTypes = listOf("Select your main farming activity", "Crop farming", "Dairy", "Poultry", "Fishery")
}
