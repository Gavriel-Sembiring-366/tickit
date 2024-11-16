package com.example.tickit.ui.jadwal

import JadwalAdapter
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
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



                val groupedByDate = jadwal.groupBy {
                    val dateTime = LocalDateTime.parse(it.waktuTayang, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    dateTime.toLocalDate()
                }

                val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.container.layoutManager = layoutManager

                val uniqueDates = groupedByDate.keys.toList()

                val adapter = JadwalAdapter(uniqueDates) { date ->
                    Toast.makeText(requireContext(), "You clicked on $date", Toast.LENGTH_SHORT).show()
                }
                binding.container.adapter = adapter

                val uniqueBioskopIds = jadwal.groupBy { it.idBioskop }.keys.toList()
                val bioskopMap = mutableMapOf<Int, String>()
                lifecycleScope.launch {
                    uniqueBioskopIds.forEach { idBioskop ->
                        val namaBioskop = repository.getNamaBioskop(idBioskop!!)
                        bioskopMap[idBioskop] = namaBioskop.toString()
                    }
                }

            } else {
                val textView = TextView(requireContext()).apply {
                    text = "No schedule available"
                    textSize = 16f
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                }
                binding.container.addView(textView)
            }
        }
        return root
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}