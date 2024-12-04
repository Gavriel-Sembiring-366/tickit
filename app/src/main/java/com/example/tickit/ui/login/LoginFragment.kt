package com.example.tickit.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tickit.R
import com.example.tickit.utils.DataStoreManager
import com.example.tickit.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var loginAdapter: LoginAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Access views
        val emailEditText: EditText = view.findViewById(R.id.editEmail1)
        val passwordEditText: EditText = view.findViewById(R.id.editpw1)
        val loginButton: Button = view.findViewById(R.id.button_masuk)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar) // Ensure you add a ProgressBar to your layout

        // Initialize LoginAdapter
        loginAdapter = LoginAdapter(fragment = this, dataStoreManager = DataStoreManager(requireContext()))

        // Attach login logic to the button
        loginAdapter.handleLogin(
            emailEditText = emailEditText,
            passwordEditText = passwordEditText,
            loginButton = loginButton,
            progressBar = progressBar
        )

        lifecycleScope.launch {
            DataStoreManager(requireContext()).getFromDataStore().collect { auth ->
                val token = auth.authToken
                if (token.isNotEmpty()) {
                    // Show the token in a Toast or handle the token
                    Toast.makeText(requireContext(), "Login TOKEN: $token", Toast.LENGTH_LONG).show()
                } else {
                    // Handle case where no token is available (e.g., user needs to log in)
                    Toast.makeText(requireContext(), "No token found. Please log in.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
