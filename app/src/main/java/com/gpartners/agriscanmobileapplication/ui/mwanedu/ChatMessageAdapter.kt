package com.gpartners.agriscanmobileapplication.ui.mwanedu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gpartners.agriscanmobileapplication.databinding.ItemChatMessageBotBinding
import com.gpartners.agriscanmobileapplication.databinding.ItemChatMessageUserBinding
import com.gpartners.agriscanmobileapplication.ui.mwanedu.ChatMessage

class ChatMessageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val messages = mutableListOf<ChatMessage>()

    fun submitList(newMessages: List<ChatMessage>) {
        messages.clear()
        messages.addAll(newMessages)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isUser) 1 else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            val binding = ItemChatMessageUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            UserMessageViewHolder(binding)
        } else {
            val binding = ItemChatMessageBotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            BotMessageViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = messages[position]
        if (holder is UserMessageViewHolder) {
            holder.bind(msg.message)
        } else if (holder is BotMessageViewHolder) {
            holder.bind(msg.message)
        }
    }

    override fun getItemCount(): Int = messages.size

    class UserMessageViewHolder(private val binding: ItemChatMessageUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(text: String) {
            binding.textUserMessage.text = text
        }
    }

    class BotMessageViewHolder(private val binding: ItemChatMessageBotBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(text: String) {
            binding.textBotMessage.text = text
        }
    }
}

