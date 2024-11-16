package com.example.tickit.ui.jadwal

import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

class DayButtonAdapter(
    private val days: List<LocalDate>,
    private val onDayClick: (LocalDate) -> Unit
) : RecyclerView.Adapter<DayButtonAdapter.DayButtonViewHolder>() {

    inner class DayButtonViewHolder(val button: Button) : RecyclerView.ViewHolder(button)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayButtonViewHolder {
        val button = Button(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        return DayButtonViewHolder(button)
    }

    override fun onBindViewHolder(holder: DayButtonViewHolder, position: Int) {
        val day = days[position]
        holder.button.text = day.toString()
        holder.button.setOnClickListener { onDayClick(day) }
    }

    override fun getItemCount(): Int = days.size
}
