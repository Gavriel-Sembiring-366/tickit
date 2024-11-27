package com.example.tickit.ui.jadwal

import android.graphics.drawable.GradientDrawable
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
import com.example.tickit.MovieDetail
import com.example.tickit.MovieDetailViewModel
import com.example.tickit.MovieDetailViewModelFactory
import com.example.tickit.R
import com.example.tickit.databinding.FragmentJadwalBinding
import com.example.tickit.entities.film.FilmRepository
import com.example.tickit.entities.jadwal.Jadwal
import com.example.tickit.entities.jadwal.JadwalRepository
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
class JadwalFragment : Fragment() {

    private var _binding: FragmentJadwalBinding? = null

    private val Jadwalrepository by lazy { JadwalRepository(requireContext()) }
    private val viewModel: JadwalViewModel by viewModels { JadwalViewModelFactory(Jadwalrepository) }
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJadwalBinding.inflate(inflater, container, false)
        val root: View = binding.root



        return root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filmRepository by lazy { FilmRepository(requireContext()) }
        val movieDetailViewModel: MovieDetailViewModel by viewModels({ requireActivity() }) {
            MovieDetailViewModelFactory(filmRepository)
        }

        movieDetailViewModel.currentFilmId.observe(viewLifecycleOwner) { filmId ->
            Toast.makeText(requireContext(), "ID FILM SINOPSIS $filmId", Toast.LENGTH_SHORT).show()
            viewModel.getJadwalByFilm(filmId)
        }

        viewModel.data.observe(viewLifecycleOwner) { jadwal ->
            if (!jadwal.isNullOrEmpty()) {
                setupScheduleView(jadwal)
            } else {
                displayNoScheduleMessage()
            }
        }


    }
    private fun displayNoScheduleMessage() {
        val textView = TextView(requireContext()).apply {
            text = "No schedule available"
            textSize = 16f
            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        }
        binding.bioskopContainer.addView(textView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupScheduleView(jadwal: List<Jadwal>) {
        val groupedByDate = jadwal.groupBy {
            LocalDateTime.parse(it.waktuTayang, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toLocalDate()
        }

        val dayButtonsAdapter = DayButtonAdapter(groupedByDate.keys.toList()) { selectedDate ->
            binding.bioskopContainer.removeAllViews()
            val schedulesForDay = groupedByDate[selectedDate] ?: emptyList()
            displaySchedulesForDay(schedulesForDay)
        }

        binding.dayContainer.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = dayButtonsAdapter
        }
    }

    private fun addBioskopHeader(namaBioskop: String) {
        val bioskopNameTextView = TextView(requireContext()).apply {
            text = namaBioskop
            textSize = 18f
            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            setPadding(8, 8, 8, 8)
        }
        binding.bioskopContainer.addView(bioskopNameTextView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun displaySchedulesForDay(schedulesForDay: List<Jadwal>) {
        val groupedByBioskop = schedulesForDay.groupBy { it.idBioskop }

        lifecycleScope.launch {
            groupedByBioskop.forEach { (idBioskop, schedules) ->
                val namaBioskop = Jadwalrepository.getBioskopById(idBioskop!!) ?: "Unknown Bioskop"

                // Create a ConstraintLayout
                val constraintLayout = ConstraintLayout(requireContext()).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setPadding(10,5,10,25)
                        setMargins(0,0,0,50)
                        setBackgroundResource(R.drawable.rounded_bottom_shadow)
                    }
                }

                // Add TextView for bioskop name
                val bioskopNameTextView = TextView(requireContext()).apply {
                    id = View.generateViewId()
                    text = namaBioskop
                    textSize = 18f
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    setPadding(20, 8, 8, 8)

                }
                constraintLayout.addView(bioskopNameTextView)

                // Create a GridLayout with 5 columns
                val gridLayout = GridLayout(requireContext()).apply {
                    id = View.generateViewId()
                    layoutParams = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    )
                    columnCount = 5

                }

                // Add buttons to the GridLayout
                schedules.forEach { schedule ->
                    gridLayout.addView(createScheduleButtons(schedule, namaBioskop))
                }
                constraintLayout.addView(gridLayout)

                // Set constraints for the TextView and GridLayout
                ConstraintSet().apply {
                    clone(constraintLayout)

                    // Constraints for TextView
                    connect(
                        bioskopNameTextView.id, ConstraintSet.TOP,
                        ConstraintSet.PARENT_ID, ConstraintSet.TOP
                    )
                    connect(
                        bioskopNameTextView.id, ConstraintSet.START,
                        ConstraintSet.PARENT_ID, ConstraintSet.START
                    )

                    // Constraints for GridLayout
                    connect(
                        gridLayout.id, ConstraintSet.TOP,
                        bioskopNameTextView.id, ConstraintSet.BOTTOM
                    )
                    connect(
                        gridLayout.id, ConstraintSet.START,
                        ConstraintSet.PARENT_ID, ConstraintSet.START
                    )
                    connect(
                        gridLayout.id, ConstraintSet.END,
                        ConstraintSet.PARENT_ID, ConstraintSet.END
                    )

                    applyTo(constraintLayout)
                }

                // Add the ConstraintLayout to the parent container
                binding.bioskopContainer.addView(constraintLayout)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createScheduleButtons(schedule: Jadwal, namaBioskop: String): Button {
        val time = LocalDateTime.parse(schedule.waktuTayang, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toLocalTime()

        return Button(requireContext()).apply {
            text = time.toString()
            textSize = 14f
            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

            val borderDrawable = GradientDrawable().apply {
                setStroke(3, ContextCompat.getColor(requireContext(), R.color.black))
                cornerRadius = 8f * context.resources.displayMetrics.density
            }
            background = borderDrawable
            layoutParams = GridLayout.LayoutParams().apply {
                width = 0
                height = LinearLayout.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f) // Span one column
                rowSpec = GridLayout.spec(GridLayout.UNDEFINED)
                setMargins(8) // Optional: Adjust margin
            }

            setOnClickListener {
                val toastMessage = "You clicked $namaBioskop at $text id: ${schedule.idJadwal}"
                Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}