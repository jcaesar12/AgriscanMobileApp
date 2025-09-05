package com.gpartners.agriscanmobileapplication.ui.disease_scanner

import java.time.LocalDateTime

data class ImageScanDTO(
    val id: Int,
    val cropTypeId: Int,
    val diagnosisResult: String? = null,
    val confidenceScore: Double? = null,
    val diagnosisDescription: String? = null,
    val recommendedAction: String? = null,
    val createdOn: LocalDateTime
)
