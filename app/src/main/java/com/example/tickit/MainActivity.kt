package com.example.tickit

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tickit.database.DBHelper
import com.example.tickit.database.MediaStoreHelper
import com.example.tickit.database.populatedata
import com.example.tickit.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

//
//        val dbHelper = DBHelper(this, null)
//        val db = dbHelper.writableDatabase
//        populatedata().populateMockData(db)
//        populatedata().populateImage(this)

        supportActionBar?.apply {
            // Enable custom view and disable default title
            setDisplayShowCustomEnabled(true)
            setDisplayShowTitleEnabled(false)

            // Inflate and set the custom view
            // Inflate the custom view
            val customView = layoutInflater.inflate(R.layout.custom_action_bar, null)

            // Set images for the custom view's ImageViews
            customView.findViewById<ImageView>(R.id.logoImage)?.setImageResource(R.drawable.tickit_logo)
            customView.findViewById<ImageView>(R.id.searchImage)?.setImageResource(R.drawable.search_svgrepo_com)
            customView.findViewById<ImageView>(R.id.userImage)?.setImageResource(R.drawable.user_circle_svgrepo_com)
            // Set the custom view with MATCH_PARENT for both width and height
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