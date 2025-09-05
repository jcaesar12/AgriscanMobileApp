package com.gpartners.agriscanmobileapplication.ui.mwanedu

import android.view.LayoutInflater
import android.view.ViewGroup
import java.text.SimpleDateFormat
import java.util.*
import androidx.recyclerview.widget.RecyclerView
import com.gpartners.agriscanmobileapplication.databinding.ItemChatHistoryBinding
import com.gpartners.agriscanmobileapplication.ui.mwanedu.ChatHistory


class ChatHistoryAdapter : RecyclerView.Adapter<ChatHistoryAdapter.HistoryViewHolder>() {

    private val history = mutableListOf<ChatHistory>()

    fun submitList(newHistory: List<ChatHistory>) {
        history.clear()
        history.addAll(newHistory)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemChatHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(history[position])
    }

    override fun getItemCount(): Int = history.size

    class HistoryViewHolder(private val binding: ItemChatHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChatHistory) {
            binding.textChatTopic.text = item.topic
            binding.textChatDate.text = formatDate(item.date) // ✅ convert Long → readable date
        }

        private fun formatDate(timestamp: Long): String {
            val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
            return sdf.format(Date(timestamp))
        }
    }
}
