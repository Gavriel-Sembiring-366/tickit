package com.example.tickit

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tickit.database.DBHelper
import com.example.tickit.database.populatedata
import com.example.tickit.databinding.ActivityMainBinding
import com.example.tickit.ui.splashScreen.SplashScreenFragment
import com.example.tickit.utils.DataStoreManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val rootView: View by lazy {
        findViewById(R.id.activity_main_container)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val dbHelper = DBHelper(this, null)
//        val db = dbHelper.writableDatabase
//        populatedata().populateMockData(db)
        populatedata().populateImage(this)

        val bottomNavView: BottomNavigationView = binding.mainActivityBottomNav
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        bottomNavView.setupWithNavController(navController)

        lifecycleScope.launch {

            val dataStoreManager by lazy { DataStoreManager(applicationContext) }

            dataStoreManager.getFromDataStore().collect { auth ->
                val token = auth.authToken
                if (token.isNotEmpty()) {
                    navController.navigate(R.id.navigation_home)
                } else {
                    navController.navigate(R.id.navigation_login)
                }
            }
        }

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

        val destinationsWithoutBottomNav = setOf(R.id.navigation_splash_screen, R.id.navigation_login)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            updatePadding(destination.id)
            if (destination.id in destinationsWithoutBottomNav) {
                binding.mainActivityBottomNav.visibility = View.GONE
            } else {
                binding.mainActivityBottomNav.visibility = View.VISIBLE
            }
        }


    }
    fun updatePadding(destinationId: Int) {
        if (destinationId == R.id.navigation_splash_screen) { // Replace with your actual destination ID
            rootView.setPadding(0, 0, 0, 0) // Remove padding
            supportActionBar?.hide() // Hide ActionBar
        }

        if (destinationId == R.id.navigation_home) {
            rootView.setPadding(16, 10, 16, 10) // Set default padding
            supportActionBar?.show() // Show ActionBar
        }
    }
}