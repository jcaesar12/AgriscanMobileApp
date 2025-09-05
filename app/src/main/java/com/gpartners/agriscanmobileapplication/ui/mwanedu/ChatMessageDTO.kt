package com.gpartners.agriscanmobileapplication.ui.mwanedu

import java.time.LocalDateTime

data class ChatMessageDTO(
    val farmerRegistrationId: Int,
    val topic: String? = null,
    val message: String,
    val sender: String,            // "Farmer" or "Bot"
    val messageType: String = "Text",
    val metadata: String? = null,
    val timestamp: LocalDateTime? = null
)