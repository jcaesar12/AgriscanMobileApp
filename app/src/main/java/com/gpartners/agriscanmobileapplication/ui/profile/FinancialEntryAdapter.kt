package com.gpartners.agriscanmobileapplication.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gpartners.agriscanmobileapplication.R
import com.gpartners.agriscanmobileapplication.ui.profile.FinancialEntry

class FinancialEntryAdapter(private val entries: List<FinancialEntry>) :
    RecyclerView.Adapter<FinancialEntryAdapter.EntryViewHolder>() {

    inner class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textCategory: TextView = itemView.findViewById(R.id.textCategoryName)
        val textDate: TextView = itemView.findViewById(R.id.textDate)
        val textAmount: TextView = itemView.findViewById(R.id.textValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_financial_entry, parent, false)
        return EntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val entry = entries[position]
        holder.textCategory.text = entry.category
        holder.textDate.text = entry.date

        val amountText = if (entry.type == "Income") {
            "+KES ${String.format("%,.2f", entry.amount)}"
        } else {
            "-KES ${String.format("%,.2f", entry.amount)}"
        }
        holder.textAmount.text = amountText

        // Color based on type
        holder.textAmount.setTextColor(
            holder.itemView.context.getColor(
                if (entry.type == "Income") android.R.color.holo_green_dark
                else android.R.color.holo_red_dark
            )
        )
    }

    override fun getItemCount(): Int = entries.size
}
