package com.example.bixi

import LoginActivity
import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bixi.ux.OnboardingActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// SplashActivity is the first screen that is shown when the app is launched
class SplashActivity : AppCompatActivity() {

    private lateinit var onBoardingScreen: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val options = ActivityOptions.makeCustomAnimation(this, android.R.anim.fade_in, android.R.anim.fade_out).toBundle()

        // Use Kotlin Coroutines to delay the execution
        CoroutineScope(Dispatchers.Main).launch {
            delay(1500)

            onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE)
            val isFirstTime = onBoardingScreen.getBoolean("firstTime", true)

            // Check if a user is logged in
            val currentUser = FirebaseAuth.getInstance().currentUser

            if (isFirstTime) {
                onBoardingScreen.edit().apply {
                    putBoolean("firstTime", false)
                    apply()
                }
                startActivity(Intent(applicationContext, OnboardingActivity::class.java), options)
            } else if (currentUser != null) {
                // User is logged in, go to the main activity
                startActivity(Intent(this@SplashActivity, MainActivity::class.java), options)
            } else {
                // User is not logged in, go to the login activity
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java), options)
            }
            finish()
        }
    }
}