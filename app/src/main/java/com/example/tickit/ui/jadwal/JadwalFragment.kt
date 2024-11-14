package com.example.tickit.ui.jadwal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tickit.databinding.FragmentJadwalBinding
import com.example.tickit.entities.jadwal.JadwalDatabase
import com.example.tickit.entities.jadwal.JadwalRepository

class JadwalFragment : Fragment() {

    private var _binding: FragmentJadwalBinding? = null
    private val repository by lazy { JadwalRepository(requireContext()) }
    private val viewModel: JadwalViewModel by viewModels { JadwalViewModelFactory(repository) }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(

        inflater: LayoutInflater,

        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentJadwalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val jadwalDatabase = JadwalDatabase.getInstance(requireContext())
        val jadwalDao = jadwalDatabase.jadwalDao()

        viewModel.getJadwalByFilm(1)
        viewModel.data.observe(viewLifecycleOwner) { jadwal ->
//            if (jadwal != null && jadwal.isNotEmpty()) {
//                val waktuTayangList = jadwal.joinToString(separator = "\n") { it.waktuTayang.toString() }
//                binding.textJadwal.text = waktuTayangList
//            } else {
//                binding.textJadwal.text = "No schedule available"
//            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}