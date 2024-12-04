package com.example.tickit.ui.login

import android.content.Context
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tickit.R
import com.example.tickit.model.Account
import com.example.tickit.utils.DataStoreManager
import com.example.tickit.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

class LoginAdapter(
    private val fragment: Fragment,
    private val dataStoreManager: DataStoreManager
) {
    fun handleLogin(
        emailEditText: EditText,
        passwordEditText: EditText,
        loginButton: Button,
        progressBar: ProgressBar
    ) {
        val loginViewModel = ViewModelProvider(fragment)[LoginViewModel::class.java]

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Validate input
            if (!isEmailValid(email)) {
                emailEditText.error = "Invalid email"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                passwordEditText.error = "Password cannot be empty"
                return@setOnClickListener
            }

            // Show loading spinner
            progressBar.visibility = View.VISIBLE
            loginButton.isEnabled = false

            // Perform login using ViewModel
            loginViewModel.loginUser(Account(email, password),dataStoreManager)

            // Observe login state
            loginViewModel.loginState.observe(fragment.viewLifecycleOwner) { state ->
                if (state.loading) {
                    progressBar.visibility = View.VISIBLE
                    loginButton.isEnabled = false
                } else {
                    progressBar.visibility = View.GONE
                    loginButton.isEnabled = true

                    if (state.isLogin) {
                        Toast.makeText(fragment.requireContext(), "Login successful!", Toast.LENGTH_LONG).show()
                        fragment.findNavController().navigate(R.id.navigation_home)
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
