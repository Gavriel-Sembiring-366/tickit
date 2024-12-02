package com.example.tickit.ui.tampilanAwal

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tickit.R
import com.example.tickit.databinding.FragmentTampilanAwalBinding

class TampilanAwal : Fragment(R.layout.fragment_tampilan_awal) {

    private var _binding: FragmentTampilanAwalBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTampilanAwalBinding.bind(view)

        binding.btnMasuk.setOnClickListener {
            findNavController().navigate(R.id.action_tampilanAwalFragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
