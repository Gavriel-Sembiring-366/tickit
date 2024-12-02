package com.example.tickit.ui.daftar

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tickit.R
import com.example.tickit.databinding.FragmentDaftarBinding

class Daftar : Fragment(R.layout.fragment_daftar) {
    // Deklarasikan ViewBinding
    private var _binding: FragmentDaftarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daftar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inisialisasi binding
        _binding = FragmentDaftarBinding.bind(view)

        // Setup click listener for the "Back" button to navigate to the login page
        binding.backDaftar.setOnClickListener {
            // Navigate to the login fragment
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        // Setup click listener for the "Daftar TICK IT" button
        binding.buttonDaftar.setOnClickListener {
            val nama = binding.editTextNama.text.toString()
            val email = binding.editEmail2.text.toString()

            if (nama.isNotEmpty() && email.isNotEmpty()) {
                // Navigate to the email verification fragment if input is valid
                findNavController().navigate(R.id.action_registerFragment_to_verifikasiEmailFragment)
            } else {
                // Show error messages if any input is empty
                if (nama.isEmpty()) {
                    binding.editTextNama.error = "Nama wajib diisi"
                }
                if (email.isEmpty()) {
                    binding.editEmail2.error = "Email wajib diisi"
                }
            }
        }
    }

    private fun validateInputs() {
        val nama = binding.editTextNama.text.toString()
        val email = binding.editEmail2.text.toString()

        // Enable/disable button based on validation
        binding.buttonDaftar.isEnabled = nama.isNotEmpty() && email.isNotEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
