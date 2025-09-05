package com.gpartners.agriscanmobileapplication.ui.carbon_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gpartners.agriscanmobileapplication.R
import com.gpartners.agriscanmobileapplication.ui.carbon_tracker.CarbonActivity


class CarbonActivityAdapter(private val activities: List<CarbonActivity>) :
    RecyclerView.Adapter<CarbonActivityAdapter.ActivityViewHolder>() {

    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: TextView = itemView.findViewById(R.id.tvActivityName)
        val textDate: TextView = itemView.findViewById(R.id.tvActivityDate)
        val textImpact: TextView = itemView.findViewById(R.id.tvCarbonDeficit)
        val dotIndicator: View = itemView.findViewById(R.id.imgDot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_activity, parent, false)
        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = activities[position]
        holder.textName.text = activity.name
        holder.textDate.text = activity.date

        // Determine dot color and impact text
        if (position > 0) {
            val prevImpact = activities[position - 1].carbonImpact
            val diff = activity.carbonImpact - prevImpact
            holder.dotIndicator.setBackgroundResource(if (diff <= 0) R.drawable.dot_green else R.drawable.dot_red)
            holder.textImpact.text = if (diff <= 0) "-${kotlin.math.abs(diff)} kg CO₂" else "+$diff kg CO₂"
            holder.textImpact.setTextColor(
                holder.itemView.context.getColor(if (diff <= 0) android.R.color.holo_green_dark else android.R.color.holo_red_dark)
            )
        } else {
            holder.dotIndicator.setBackgroundResource(R.drawable.dot_green)
            holder.textImpact.text = "${activity.carbonImpact} kg CO₂"
        }
    }

    override fun getItemCount(): Int = activities.size
}
