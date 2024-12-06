package com.example.tickit.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tickit.R
import com.example.tickit.utils.DataStoreManager

class RegisterFragment : Fragment() {
    private lateinit var registerAdapter: RegisterAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerAdapter = RegisterAdapter(fragment = this, dataStoreManager = DataStoreManager(requireContext()))

        registerAdapter.goToLogin(goToLogin = view.findViewById(R.id.goToLogin))
        registerAdapter.handleRegister(
            editTextNamaLengkap = view.findViewById(R.id.editTextNamaLengkap),
            editUserName = view.findViewById(R.id.editUserName),
            editEmail = view.findViewById(R.id.editEmail),
            editPassword = view.findViewById(R.id.editPassword),
            buttonDaftar = view.findViewById(R.id.registerButton),
            progressBar = view.findViewById(R.id.registerProgressBar)
        )



    }
}