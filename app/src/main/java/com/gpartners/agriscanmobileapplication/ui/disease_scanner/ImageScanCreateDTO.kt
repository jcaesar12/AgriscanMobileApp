package com.gpartners.agriscanmobileapplication.ui.disease_scanner

data class ImageScanCreateDTO(
    val farmerId: Int,
    val cropTypeId: Int,
    val diagnosisDescription: String? = null,
    val imageMetadata: Map<String, Any>? = null,
    val geolocation: String? = null,
    val deviceTypeId: Int? = null,
    val diagnosisResult: String? = null,
    val recommendedAction: String? = null,
    val confidenceScore: Double? = null
)