package com.example.bixi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// MainActivity is the main screen that hosts the navigation components
class MainActivity : AppCompatActivity() {

    // Variable to track if the back button was pressed twice
    private var doubleBackToExitPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        overridePendingTransition(androidx.appcompat.R.anim.abc_fade_in, com.google.android.material.R.anim.abc_fade_out)

        // Set up the navigation components
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        NavigationUI.setupWithNavController(bottomNav, navController)
    }

    // Handle the back button press to exit the app
    override fun onBackPressed() {
        if (doubleBackToExitPressed) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressed = true
        Toast.makeText(this, "Tap again to exit", Toast.LENGTH_SHORT).show()

        // Use Kotlin Coroutines to delay the execution
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            doubleBackToExitPressed = false
        }
    }
}