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
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
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
import java.time.format.TextStyle
import java.util.Locale
import androidx.lifecycle.lifecycleScope
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

                            val bioskopGridLayout = GridLayout(requireContext()).apply {
                                layoutParams = LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                                )

                                columnCount = 4 // Adjust as needed
                                setPadding(4, 4, 4, 4)
                            }

                            schedules.forEach { schedule ->
                                val time = LocalDateTime.parse(
                                    schedule.waktuTayang,
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                ).toLocalTime()

                                val timeButton = Button(requireContext()).apply {
                                    text = time.toString()
                                    textSize = 14f
                                    setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                                    setPadding(8, 4, 8, 4)
                                    layoutParams = GridLayout.LayoutParams().apply {
                                        setMargins(4, 4, 4, 4)
                                    }

                                    background = ColorDrawable(ContextCompat.getColor(requireContext(), R.color.white))
                                    setOnClickListener {
                                        val toastMessage = "You clicked $namaBioskop at $text"
                                        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
                                    }
                                }
                                bioskopGridLayout.addView(timeButton)
                            }

                            binding.bioskopContainer.addView(bioskopGridLayout)
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