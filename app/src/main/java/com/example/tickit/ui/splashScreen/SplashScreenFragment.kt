package com.example.tickit.ui.splashScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tickit.R
import com.example.tickit.utils.DataStoreManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashScreenFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataStoreManager = DataStoreManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.getFromDataStore().collect { auth ->
                val token = auth.authToken
                val navController = findNavController()

                if (token.isNotEmpty()) {
                    navController.navigate(R.id.navigation_home)
                } else {
                    navController.navigate(R.id.navigation_login)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }
}
