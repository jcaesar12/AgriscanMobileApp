package com.gpartners.agriscanmobileapplication.ui.mwanedu

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.gpartners.agriscanmobileapplication.R

class MwaneduActivity : AppCompatActivity() {

    private lateinit var tabCurrentChat: TextView
    private lateinit var tabChatHistory: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_chatbot_page)

        // Tabs
        tabCurrentChat = findViewById(R.id.tabCurrentChat)
        tabChatHistory = findViewById(R.id.tabChatHistory)

        // Default = Current Chat
        showCurrentChat()

        tabCurrentChat.setOnClickListener {
            setActiveTab(isCurrentChat = true)
            showCurrentChat()
        }

        tabChatHistory.setOnClickListener {
            setActiveTab(isCurrentChat = false)
            showChatHistory()
        }
    }

    private fun setActiveTab(isCurrentChat: Boolean) {
        if (isCurrentChat) {
            tabCurrentChat.setBackgroundResource(R.drawable.tab_active_bg)
            tabCurrentChat.setTextColor(getColor(R.color.green))

            tabChatHistory.setBackgroundResource(android.R.color.transparent)
            tabChatHistory.setTextColor(getColor(R.color.grey))
        } else {
            tabChatHistory.setBackgroundResource(R.drawable.tab_active_bg)
            tabChatHistory.setTextColor(getColor(R.color.green))

            tabCurrentChat.setBackgroundResource(android.R.color.transparent)
            tabCurrentChat.setTextColor(getColor(R.color.grey))
        }
    }

    private fun showCurrentChat() {
        supportFragmentManager.commit {
            replace(R.id.chatContainer, CurrentChatFragment())
        }
    }

    private fun showChatHistory() {
        supportFragmentManager.commit {
            replace(R.id.chatContainer, ChatHistoryFragment())
        }
    }
}

