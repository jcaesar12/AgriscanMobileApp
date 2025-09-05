package com.gpartners.agriscanmobileapplication.ui.mwanedu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gpartners.agriscanmobileapplication.ui.retrofitinstance.queryChatbot
import java.util.UUID

class MwaneduViewModel : ViewModel() {

    private val _isChatActive = MutableLiveData(false)
    val isChatActive: LiveData<Boolean> get() = _isChatActive

    private val _messages = MutableLiveData<List<ChatMessage>>(emptyList())
    val messages: LiveData<List<ChatMessage>> get() = _messages

    private val _chatHistory = MutableLiveData<List<ChatHistory>>(emptyList())
    val chatHistory: LiveData<List<ChatHistory>> get() = _chatHistory

    fun startChat() {
        _isChatActive.value = true
        _messages.value = emptyList()
    }

    fun sendUserMessage(text: String) {
        // Add user message to chat
        val newList = _messages.value.orEmpty().toMutableList()
        newList.add(
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = text,
                sender = Sender.USER,
                timestamp = System.currentTimeMillis(),
                isUser = true
            )
        )
        _messages.value = newList

        // 🔗 Call backend using Retrofit
        queryChatbot(text) { answer ->
            if (answer.isNotEmpty()) {
                sendBotMessage(answer)   // no .first()
            } else {
                sendBotMessage("Sorry, I couldn’t find an answer.")
            }
        }

    }

    /**
     * Simple relevance scoring: counts overlapping words between user input and KB question.
     * You can replace this with something more advanced later (e.g., embeddings, cosine similarity).
     */
    private fun relevanceScore(userInput: String, kbQuestion: String): Int {
        val userWords = userInput.lowercase().split(" ").toSet()
        val kbWords = kbQuestion.lowercase().split(" ").toSet()
        return userWords.intersect(kbWords).size
    }


    private fun sendBotMessage(text: String) {
        val newList = _messages.value.orEmpty().toMutableList()
        newList.add(
            ChatMessage(
                id = UUID.randomUUID().toString(),
                message = text,
                sender = Sender.BOT,
                timestamp = System.currentTimeMillis(),
                isUser = false
            )
        )
        _messages.value = newList
    }

    fun saveChatToHistory(title: String) {
        val newHistory = _chatHistory.value.orEmpty().toMutableList()
        newHistory.add(
            ChatHistory(
                id = UUID.randomUUID().toString(),
                topic = title,
                messageCount = _messages.value?.size ?: 0,
                date = System.currentTimeMillis()
            )
        )
        _chatHistory.value = newHistory
        _isChatActive.value = false
    }
}
