package com.example.busfinder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    // Declare EditText variables at the class level
    private lateinit var etPhone: EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize views
        etPhone = findViewById(R.id.etPhone)
        etPassword = findViewById(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)

        // Handle Login Button Click
        btnLogin.setOnClickListener {
            val phone = etPhone.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Debug: Log inputs
            Log.d("LoginActivity", "Phone: $phone, Password: $password")

            // Validate inputs
            if (validateInputs(phone, password)) {
                // Retrieve saved user data
                val sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
                val savedPhone = sharedPreferences.getString("Phone", "")
                val savedPassword = sharedPreferences.getString("Password", "")

                // Debug: Log saved data
                Log.d("LoginActivity", "Saved Phone: $savedPhone, Saved Password: $savedPassword")

                // Check credentials
                if (phone == savedPhone && password == savedPassword) {
                    // Redirect to MainActivity
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Invalid phone number or password", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Handle Sign-Up Text Click
        tvSignUp.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish()
        }
    }

    private fun validateInputs(phone: String, password: String): Boolean {
        // Check if phone number is empty
        if (phone.isEmpty()) {
            etPhone.error = "Phone number is required"
            return false
        }

        // Validate phone number (must be exactly 11 digits and numeric)
        if (phone.length != 11 || !phone.all { it.isDigit() }) {
            etPhone.error = "Phone number must be 11 digits"
            return false
        }

        // Check if password is empty
        if (password.isEmpty()) {
            etPassword.error = "Password is required"
            return false
        }

        // Validate password (must be exactly 5 digits and numeric)
        if (password.length != 5 || !password.all { it.isDigit() }) {
            etPassword.error = "Password must be 5 digits"
            return false
        }

        return true
    }
}
