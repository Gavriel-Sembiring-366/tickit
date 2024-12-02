package com.example.tickit.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.AppCompatImageButton
import androidx.navigation.fragment.findNavController
import com.example.tickit.R

class Login : Fragment() {

    // Deklarasi elemen UI
    private lateinit var barLogin: View
    private lateinit var masukText: TextView
    private lateinit var backButton: AppCompatImageButton
    private lateinit var emailLabel: TextView
    private lateinit var emailEditText: EditText
    private lateinit var passwordLabel: TextView
    private lateinit var passwordEditText: EditText
    private lateinit var lupaPwText: TextView
    private lateinit var resetPwText: TextView
    private lateinit var loginButton: Button
    private lateinit var belumDaftarText: TextView
    private lateinit var daftarButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Menginflate layout fragment_login
        val binding = inflater.inflate(R.layout.fragment_login, container, false)

        // Inisialisasi elemen UI
        barLogin = binding.findViewById(R.id.bar_login)
        masukText = binding.findViewById(R.id.masuk)
        backButton = binding.findViewById(R.id.back_login)
        emailLabel = binding.findViewById(R.id.email_masuk)
        emailEditText = binding.findViewById(R.id.editEmail1)
        passwordLabel = binding.findViewById(R.id.password)
        passwordEditText = binding.findViewById(R.id.editpw1)
        lupaPwText = binding.findViewById(R.id.lupa_pw)
        resetPwText = binding.findViewById(R.id.reset_pw)
        loginButton = binding.findViewById(R.id.button_masuk)
        belumDaftarText = binding.findViewById(R.id.belum_daftar)
        daftarButton = binding.findViewById(R.id.button_daftar)

        // Listener untuk tombol "Masuk"
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            // Tambahkan logika untuk login di sini
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Navigasi ke halaman beranda setelah login berhasil
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                // Beri feedback kepada pengguna jika input kosong
                emailEditText.error = "Email tidak boleh kosong"
                passwordEditText.error = "Password tidak boleh kosong"
            }
        }

        // Listener untuk tombol "Daftar Sekarang"
        daftarButton.setOnClickListener {
            // Navigasi ke halaman pendaftaran
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        // Listener untuk tombol "Reset Password"
        resetPwText.setOnClickListener {
            // Navigasi ke halaman reset password
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
        }

        // Listener untuk tombol "Kembali"
        backButton.setOnClickListener {
            // Logika untuk kembali ke halaman sebelumnya
            findNavController().navigate(R.id.action_loginFragment_to_tampilanAwalFragment)
        }

        return binding
    }
}
