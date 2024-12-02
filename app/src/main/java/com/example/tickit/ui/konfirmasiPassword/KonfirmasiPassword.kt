package com.example.tickit.ui.konfirmasiPassword

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tickit.R
import com.example.tickit.databinding.FragmentKonfirmasiPasswordBinding

class KonfirmasiPassword : Fragment() {

    private var _binding: FragmentKonfirmasiPasswordBinding? = null
    private val binding get() = _binding!!

    private var isPasswordVisible = false
    private var isConfirmPasswordVisible = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKonfirmasiPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() {
        var isPasswordVisible = false
        var isConfirmPasswordVisible = false

        // Back button action
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_konfirmasiPassword_to_verifikasiEmailFragment)
        }

        // Toggle password visibility for Buat Password
        binding.imageViewEye.setOnClickListener {
            isPasswordVisible = togglePasswordVisibility(binding.editTextBuatPassword, binding.imageViewEye, isPasswordVisible)
        }

        // Toggle password visibility for Konfirmasi Password
        binding.imageViewEye2.setOnClickListener {
            isConfirmPasswordVisible = togglePasswordVisibility(binding.editTextKonfirmasiPassword, binding.imageViewEye2, isConfirmPasswordVisible)
        }

        // Save Password button action
        binding.btnSave.setOnClickListener {
            val buatPassword = binding.editTextBuatPassword.text.toString()
            val konfirmasiPassword = binding.editTextKonfirmasiPassword.text.toString()

            if (buatPassword.isEmpty() || konfirmasiPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (buatPassword != konfirmasiPassword) {
                Toast.makeText(requireContext(), "Password tidak cocok", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Navigate to Daftar Sukses Fragment
            findNavController().navigate(R.id.action_konfirmasiPassword_to_daftarSuksesFragment)
        }
    }



    private fun togglePasswordVisibility(editText: EditText, imageView: ImageView, isVisible: Boolean): Boolean {
        if (isVisible) {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            imageView.setImageResource(R.drawable.ic_eye)
        } else {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            imageView.setImageResource(R.drawable.ic_eye_open)
        }
        // Retain cursor position
        editText.setSelection(editText.text.length)
        return !isVisible
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}