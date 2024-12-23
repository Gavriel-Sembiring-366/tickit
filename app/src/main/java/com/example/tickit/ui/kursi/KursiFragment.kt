package com.example.tickit.ui.kursi

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.LayerDrawable
import android.icu.lang.UCharacter.IndicPositionalCategory.RIGHT
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tickit.MovieDetailViewModel
import com.example.tickit.R
import com.example.tickit.viewmodel.BangkuTersediaViewModel
import kotlinx.coroutines.launch
class KursiFragment : Fragment() {
    private val bangkuTersediaViewModel: BangkuTersediaViewModel by viewModels()
    private val selectedSeats = mutableSetOf<String>() // Track selected seats
    val movieDetailViewModel: MovieDetailViewModel by activityViewModels()
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_kursi, container, false)
        val constraintLayout = view.findViewById<ConstraintLayout>(R.id.kursiConstraintLayout)

        val movieDetailViewModel: MovieDetailViewModel by activityViewModels()
        movieDetailViewModel.currentJadwalId.observe(viewLifecycleOwner) { jadwalId ->
            jadwalId?.let {
//                Toast.makeText(requireContext(), "Received jadwalId: $it", Toast.LENGTH_SHORT).show()

                lifecycleScope.launch {
                    val bangkuTersediaList = bangkuTersediaViewModel.getBangkuTersediaByJadwalId(it)

                    if (bangkuTersediaList.isNotEmpty()) {
                        val seatMap = bangkuTersediaList.associateBy(
                            { it.nomor_bangku }, // Key: nomor_bangku
                            { it.bangku_tersedia_id }
                        )

                        val availableSeats = seatMap.keys // Get the nomor_bangku
                        val buttonIds = mutableMapOf<String, Int>()
                        val constraintSet = ConstraintSet()

                        constraintSet.clone(constraintLayout)

                        val width = Resources.getSystem().displayMetrics.widthPixels
                        val buttonSize = ((width - 40) / 11) - 10
                        val margin = 10

                        for ((rowIndex, row) in ('A'..'K').withIndex()) {
                            for (column in 1..11) {
                                val nomorBangku = "$row$column"

                                val button = Button(requireContext()).apply {
                                    id = View.generateViewId()
                                    text = nomorBangku

                                    // Apply background dynamically with both color and drawable
                                    setBackgroundResource(R.drawable.rounded_border)
                                    setBackgroundResource(if (nomorBangku in availableSeats) R.drawable.available_button else R.drawable.unavailable_button)
                                    isEnabled = nomorBangku in availableSeats
                                    setPadding(0, 0, 0, 0)
                                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)

                                    setOnClickListener {
                                        if (selectedSeats.contains(nomorBangku)) {
                                            selectedSeats.remove(nomorBangku)
                                            setBackgroundResource(R.drawable.available_button) // Deselect
                                        } else {
                                            selectedSeats.add(nomorBangku)
                                            setBackgroundResource(R.drawable.selected_button) // Select
                                        }
                                        val selectedKursiTextView = view.findViewById<TextView>(R.id.selectedKursi)
                                        selectedKursiTextView.text = selectedSeats.toString().replace("[", "").replace("]", "")
                                        movieDetailViewModel.setSelectedSeatsNomor(selectedSeats.toList())
                                        val totalHarga = selectedSeats.size * 35000
                                        val totalHargaTextView = view.findViewById<TextView>(R.id.totalHarga)
                                        totalHargaTextView.text = "Rp $totalHarga"
                                    }
                                }

                                constraintLayout.addView(button)
                                buttonIds[nomorBangku] = button.id

                                val buttonId = button.id
                                constraintSet.constrainWidth(buttonId, buttonSize)
                                constraintSet.constrainHeight(buttonId, buttonSize)

                                if (column == 1) {
                                    constraintSet.connect(
                                        buttonId,
                                        ConstraintSet.START,
                                        ConstraintSet.PARENT_ID,
                                        ConstraintSet.START,
                                        margin
                                    )
                                } else if (column == 7) {
                                    val previousButtonId = buttonIds["$row${column - 1}"]!!
                                    constraintSet.connect(
                                        buttonId,
                                        ConstraintSet.START,
                                        previousButtonId,
                                        ConstraintSet.END,
                                        margin + 40
                                    )
                                } else {
                                    val previousButtonId = buttonIds["$row${column - 1}"]!!
                                    constraintSet.connect(
                                        buttonId,
                                        ConstraintSet.START,
                                        previousButtonId,
                                        ConstraintSet.END,
                                        margin
                                    )
                                }

                                if (rowIndex == 0) {
                                    constraintSet.connect(
                                        buttonId,
                                        ConstraintSet.TOP,
                                        ConstraintSet.PARENT_ID,
                                        ConstraintSet.TOP,
                                        margin
                                    )
                                } else {
                                    val topButtonId = buttonIds["${(row - 1)}$column"]!!
                                    constraintSet.connect(
                                        buttonId,
                                        ConstraintSet.TOP,
                                        topButtonId,
                                        ConstraintSet.BOTTOM,
                                        margin
                                    )
                                }
                            }
                        }

                        // Add the bottom button
                        val confirmButton = Button(requireContext()).apply {
                            id = View.generateViewId()
                            text = "KONFIRMASI BANGKU"
                            setTextColor(Color.WHITE)
                            textSize = 16f
                            typeface = Typeface.DEFAULT_BOLD
                            setBackgroundResource(R.drawable.available_button) // Use a drawable resource for the rounded blue style
                            setPadding(32, 16, 32, 16) // Adjust padding for a balanced look
                            setOnClickListener {
                                val selectedBangkuIds = selectedSeats.mapNotNull { seatMap[it] }
                                movieDetailViewModel.setSelectedSeatsId(selectedBangkuIds)

                                findNavController().navigate(R.id.navigation_pembayaran)
                            }
                        }

                        constraintLayout.addView(confirmButton)
                        val confirmButtonId = confirmButton.id
                        constraintSet.constrainWidth(
                            confirmButtonId,
                            ConstraintSet.MATCH_CONSTRAINT
                        )

                        constraintSet.constrainHeight(confirmButtonId, ConstraintSet.WRAP_CONTENT)
                        constraintSet.connect(
                            confirmButtonId,
                            ConstraintSet.TOP,
                            buttonIds["K11"]!!,
                            ConstraintSet.BOTTOM,
                            margin + 40
                        )
                        constraintSet.connect(
                            confirmButtonId,
                            ConstraintSet.START,
                            ConstraintSet.PARENT_ID,
                            ConstraintSet.START,
                            margin
                        )
                        constraintSet.connect(
                            confirmButtonId,
                            ConstraintSet.END,
                            ConstraintSet.PARENT_ID,
                            ConstraintSet.END,
                            margin + 50
                        )
                        constraintSet.applyTo(constraintLayout)
                    } else {
//                        Toast.makeText(requireContext(), "No seats available", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return view
    }

}
