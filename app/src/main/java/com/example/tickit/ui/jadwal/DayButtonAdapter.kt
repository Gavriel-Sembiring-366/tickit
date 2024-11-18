package com.example.tickit.ui.jadwal

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tickit.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class DayButtonAdapter(
    private val days: List<LocalDate>,
    private val onDayClick: (LocalDate) -> Unit
) : RecyclerView.Adapter<DayButtonAdapter.DayButtonViewHolder>() {
    private var currentlyActiveButton: Button? = null
    inner class DayButtonViewHolder(val button: Button) : RecyclerView.ViewHolder(button)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayButtonViewHolder {
        val button = Button(parent.context).apply {
            textSize = 14f
            setTextColor(ContextCompat.getColor(parent.context, R.color.black))
            background = ColorDrawable(ContextCompat.getColor(parent.context, R.color.white)) // Default background
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(8, 8, 8, 8) // Add some margins
            }
        }
        return DayButtonViewHolder(button)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DayButtonViewHolder, position: Int) {
        val date = days[position]
        val dateText = date.format(DateTimeFormatter.ofPattern("dd-MMM", Locale.getDefault()))
        val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())

        holder.button.text = "$dateText \n $dayOfWeek"

        val day = days[position]
        holder.button.setOnClickListener {
            onDayClick(day)
            currentlyActiveButton?.apply {
                background = ColorDrawable(ContextCompat.getColor(it.context, R.color.white))
            }
            holder.button.background = ColorDrawable(ContextCompat.getColor(it.context, R.color.blue))
            currentlyActiveButton = holder.button
        }
    }

    override fun getItemCount(): Int = days.size
}
