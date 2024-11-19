package com.example.tickit.ui.jadwal

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tickit.R
import com.example.tickit.databinding.FragmentJadwalBinding
import com.example.tickit.entities.jadwal.JadwalRepository
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
class JadwalFragment : Fragment() {

    private var _binding: FragmentJadwalBinding? = null
    private val repository by lazy { JadwalRepository(requireContext()) }
    private val viewModel: JadwalViewModel by viewModels { JadwalViewModelFactory(repository) }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(

        inflater: LayoutInflater,

        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJadwalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.getJadwalByFilm(1)

        viewModel.data.observe(viewLifecycleOwner) { jadwal ->
            if (jadwal != null && jadwal.isNotEmpty()) {
                // Group schedules by day
                val groupedByDate = jadwal.groupBy {
                    val dateTime = LocalDateTime.parse(it.waktuTayang, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    dateTime.toLocalDate()
                }

                val dayButtonsAdapter = DayButtonAdapter(groupedByDate.keys.toList()) { selectedDate ->
                    binding.bioskopContainer.removeAllViews()

                    val schedulesForDay = groupedByDate[selectedDate] ?: emptyList()

                    val groupedByBioskop = schedulesForDay.groupBy { it.idBioskop }
                    lifecycleScope.launch {
                        groupedByBioskop.forEach { (idBioskop, schedules) ->

                            val namaBioskop = repository.getBioskopById(idBioskop!!) ?: "Unknown Bioskop"

                            val bioskopNameTextView = TextView(requireContext()).apply {
                                text = namaBioskop
                                textSize = 18f
                                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                                setPadding(8, 8, 8, 8)
                            }
                            binding.bioskopContainer.addView(bioskopNameTextView)


                            schedules.forEach { schedule ->
                                // Create a new buttonLayout for each schedule
                                val buttonLayout = ConstraintLayout(requireContext()).apply {
                                    layoutParams = ConstraintLayout.LayoutParams(
                                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                                    ).apply { setMargins(4, 0, 4, 20) }
                                }

                                val time = LocalDateTime.parse(
                                    schedule.waktuTayang,
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                ).toLocalTime()

                                // Create buttons dynamically
                                val leftTimeButton = Button(requireContext()).apply {
                                    id = View.generateViewId()
                                    text = time.toString()
                                    textSize = 12f
                                    setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                                    background = ColorDrawable(ContextCompat.getColor(requireContext(), R.color.white))
                                    layoutParams = LinearLayout.LayoutParams(
                                        150,
                                        100
                                    )
                                    setOnClickListener {
                                        val toastMessage = "You clicked $namaBioskop at $text id: ${schedule.idJadwal}"
                                        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
                                    }
                                }

                                val centerTimeButton = Button(requireContext()).apply {
                                    id = View.generateViewId()
                                    text = time.toString()
                                    textSize = 12f
                                    setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                                    background = ColorDrawable(ContextCompat.getColor(requireContext(), R.color.white))
                                    layoutParams = LinearLayout.LayoutParams(
                                        150,
                                        100
                                    )
                                    setOnClickListener {
                                        val toastMessage = "You clicked $namaBioskop at $text id: ${schedule.idJadwal}"
                                        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
                                    }
                                }

                                val rightTimeButton = Button(requireContext()).apply {
                                    id = View.generateViewId()
                                    text = time.toString()
                                    textSize = 12f
                                    setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                                    background = ColorDrawable(ContextCompat.getColor(requireContext(), R.color.white))
                                    layoutParams = LinearLayout.LayoutParams(
                                        150,
                                        100
                                    )
                                    setOnClickListener {
                                        val toastMessage = "You clicked $namaBioskop at $text id: ${schedule.idJadwal}"
                                        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
                                    }
                                }

                                buttonLayout.addView(leftTimeButton)
                                buttonLayout.addView(centerTimeButton)
                                buttonLayout.addView(rightTimeButton)

                                ConstraintSet().apply {
                                    clone(buttonLayout)

                                    connect(leftTimeButton.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
                                    connect(leftTimeButton.id, ConstraintSet.END, centerTimeButton.id, ConstraintSet.START)

                                    connect(centerTimeButton.id, ConstraintSet.START, leftTimeButton.id, ConstraintSet.END)
                                    connect(centerTimeButton.id, ConstraintSet.END, rightTimeButton.id, ConstraintSet.START)

                                    connect(rightTimeButton.id, ConstraintSet.START, centerTimeButton.id, ConstraintSet.END)
                                    connect(rightTimeButton.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

                                    setHorizontalWeight(leftTimeButton.id, 1f)
                                    setHorizontalWeight(centerTimeButton.id, 1f)
                                    setHorizontalWeight(rightTimeButton.id, 1f)

                                    applyTo(buttonLayout)
                                }

                                // Add the newly created buttonLayout to bioskopContainer
                                binding.bioskopContainer.addView(buttonLayout)
                            }

                        }
                    }
                }
                binding.dayContainer.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.dayContainer.adapter = dayButtonsAdapter
            } else {
                val textView = TextView(requireContext()).apply {
                    text = "No schedule available"
                    textSize = 16f
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                }
                binding.bioskopContainer.addView(textView)
            }
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}