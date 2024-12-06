package com.example.tickit.ui.register

import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tickit.R
import com.example.tickit.model.Account
import com.example.tickit.model.RegisterData
import com.example.tickit.utils.DataStoreManager
import com.example.tickit.viewmodel.LoginViewModel
import com.example.tickit.viewmodel.RegisterViewModel

class RegisterAdapter(
    private val fragment: Fragment,
    private val dataStoreManager: DataStoreManager
) {
    fun goToLogin(
        goToLogin : ImageView
    ){
        goToLogin.setOnClickListener {
            fragment.findNavController().navigate(R.id.navigation_login)
        }
    }

    fun handleRegister(
        editTextNamaLengkap: EditText,
        editUserName: EditText,
        editEmail: EditText,
        editPassword: EditText,
        buttonDaftar: Button,
        progressBar: ProgressBar,
    ) {
        val registerViewModel = ViewModelProvider(fragment)[RegisterViewModel::class.java]

        buttonDaftar.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val userName = editUserName.text.toString()
            val password = editPassword.text.toString()
            val fullName = editTextNamaLengkap.text.toString()

            // Validate input
            if (!isEmailValid(email)) {
                editEmail.error = "l'Invalid email"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                editPassword.error = "Password cannot be empty"
                return@setOnClickListener
            }

            // Show loading spinner
            progressBar.visibility = View.VISIBLE
            buttonDaftar.isEnabled = false

            // Perform login using ViewModel
            registerViewModel.registerUser(
                RegisterData(
                    email = email,
                    fullName = fullName,
                    userName = userName,
                    password = password
                )
                ,dataStoreManager)

            registerViewModel.registerState.observe(fragment.viewLifecycleOwner) { state ->
                if (state.loading) {
                    progressBar.visibility = View.VISIBLE
                    buttonDaftar.isEnabled = false
                } else {
                    progressBar.visibility = View.GONE
                    buttonDaftar.isEnabled = true

                    if (state.isRegister) {
                        Toast.makeText(fragment.requireContext(), "Register successful!", Toast.LENGTH_LONG).show()
                        fragment.findNavController().navigate(R.id.navigation_login)
                    } else if (state.error != null) {
                        Toast.makeText(fragment.requireContext(), state.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }



    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}