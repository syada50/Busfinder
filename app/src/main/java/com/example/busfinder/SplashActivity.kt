package com.example.busfinder

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout for the splash screen
        setContentView(R.layout.activity_splash) // Make sure you have a layout file named `activity_splash.xml`

        // Delay for 3 seconds (3000 milliseconds)
        Handler(Looper.getMainLooper()).postDelayed({
            // Redirect to MainActivity
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish() // Close the splash activity
        }, 3000)
    }
}