package com.example.finalproject.presentation

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.finalproject.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        val navController = navHost.navController
        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottom_navigation).apply {
                setupWithNavController(navController)
                setOnItemReselectedListener { }
            }

        val topLevelDestinations = setOf(
            R.id.homeFragment,
            R.id.myListsFragment,
            R.id.browseFragment
        )

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            bottomNavigationView.isVisible = topLevelDestinations.contains(destination.id)
        }
    }
}