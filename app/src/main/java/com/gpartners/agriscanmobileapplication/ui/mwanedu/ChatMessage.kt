package com.gpartners.agriscanmobileapplication.ui.mwanedu

data class ChatMessage(
    val id: String,
    val message: String,
    val sender: Sender,
    val timestamp: Long,
    val isUser: Boolean
)

enum class Sender { BOT, USER }

