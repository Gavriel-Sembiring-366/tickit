package com.example.tickit

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tickit.databinding.ActivityMainBinding
import com.example.tickit.utils.DataStoreManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        lifecycleScope.launch {
            // Use the applicationContext for DataStoreManager
            val dataStoreManager = DataStoreManager(applicationContext)

            dataStoreManager.getFromDataStore().collect { auth ->
                val token = auth.authToken
                if (token.isNotEmpty()) {
                    // Show the token in a Toast or handle the token
                    Toast.makeText(this@MainActivity, "Login TOKEN: $token", Toast.LENGTH_LONG).show()
                } else {
                    // Handle case where no token is available (e.g., user needs to log in)
                    Toast.makeText(this@MainActivity, "No token found. Please log in.", Toast.LENGTH_LONG).show()
                }
            }
        }


        // Set up custom ActionBar
        supportActionBar?.apply {
            setDisplayShowCustomEnabled(true)
            setDisplayShowTitleEnabled(false)

            val customView = layoutInflater.inflate(R.layout.custom_action_bar, null)
            customView.findViewById<ImageView>(R.id.logoImage)?.setImageResource(R.drawable.tickit_logo)
            customView.findViewById<ImageView>(R.id.searchImage)?.setImageResource(R.drawable.search_svgrepo_com)
            customView.findViewById<ImageView>(R.id.userImage)?.setImageResource(R.drawable.user_circle_svgrepo_com)

            setCustomView(
                customView,
                ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT
                )
            )
        }
    }

}