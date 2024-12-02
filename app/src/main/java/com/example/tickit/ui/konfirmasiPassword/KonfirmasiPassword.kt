package com.example.tickit

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tickit.databinding.FragmentKonfirmasiPasswordBinding

class KonfirmasiPassword : Fragment() {

    private var _binding: FragmentKonfirmasiPasswordBinding? = null
    private val binding get() = _binding!!

    private var isPasswordVisible = false
    private var isConfirmPasswordVisible = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentKonfirmasiPasswordBinding.bind(view)

        setupListeners()
    }

    private fun setupListeners() {
        // Back button action
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_konfirmasiPassword_to_verifikasiEmail)
        }

        // Toggle password visibility for Buat Password
        binding.imageViewEye.setOnClickListener {
            togglePasswordVisibility(binding.editTextBuatPassword, binding.imageViewEye, ::isPasswordVisible)
            isPasswordVisible = !isPasswordVisible
        }

        // Toggle password visibility for Konfirmasi Password
        binding.imageViewEye2.setOnClickListener {
            togglePasswordVisibility(binding.editTextKonfirmasiPassword, binding.imageViewEye2, ::isConfirmPasswordVisible)
            isConfirmPasswordVisible = !isConfirmPasswordVisible
        }

        // Save password button
        binding.btnSave.setOnClickListener {
            val password = binding.editTextBuatPassword.text.toString()
            val confirmPassword = binding.editTextKonfirmasiPassword.text.toString()

            if (password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (password.length < 8 || password.any { !it.isLetterOrDigit() }) {
                Toast.makeText(requireContext(), "Password harus minimal 8 karakter tanpa simbol", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(requireContext(), "Password dan konfirmasi password tidak sama", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(R.id.action_konfirmasiPassword_to_daftarSukses)
            }
        }
    }

    private fun togglePasswordVisibility(editText: View, imageView: ImageView, isVisible: Boolean) {
        if (editText is androidx.appcompat.widget.AppCompatEditText) {
            if (isVisible) {
                editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                imageView.setImageResource(R.drawable.ic_eye)
            } else {
                editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                imageView.setImageResource(R.drawable.ic_eye_open)
            }
            // Retain cursor position
            editText.setSelection(editText.text.length)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
