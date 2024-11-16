import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tickit.R
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
class JadwalAdapter(
    private val uniqueDates: List<java.time.LocalDate>,  // Now we have unique dates
    private val onButtonClick: (java.time.LocalDate) -> Unit
) : RecyclerView.Adapter<JadwalAdapter.JadwalViewHolder>() {

    private var currentlyActiveButton: Button? = null // Track the currently active (blue) button

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalViewHolder {
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
        return JadwalViewHolder(button)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: JadwalViewHolder, position: Int) {
        val date = uniqueDates[position]
        val dateText = date.format(DateTimeFormatter.ofPattern("dd-MMM", Locale.getDefault()))
        val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())

        holder.button.text = "$dateText \n $dayOfWeek"

        holder.button.setOnClickListener {
            // Reset background of all buttons to white
            currentlyActiveButton?.apply {
                background = ColorDrawable(ContextCompat.getColor(it.context, R.color.white))
            }

            // Set this button's background to blue
            holder.button.background = ColorDrawable(ContextCompat.getColor(it.context, R.color.blue))

            // Update the reference of the currently active button
            currentlyActiveButton = holder.button

            // Trigger the callback function
            onButtonClick(date)
        }
    }

    override fun getItemCount(): Int = uniqueDates.size

    inner class JadwalViewHolder(val button: Button) : RecyclerView.ViewHolder(button)
}
