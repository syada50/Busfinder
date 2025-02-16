package com.example.busfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Initialize views
        val etName = findViewById<EditText>(R.id.etName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)

        // Handle Register Button Click
        btnRegister.setOnClickListener {
            val name = etName.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            // Validate inputs
            if (validateInputs(name, phone, password, confirmPassword)) {
                // Save user data (e.g., SharedPreferences or Firebase)
                saveUserData(name, phone, password)

                // Redirect to Login Screen
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        // Handle Login Text Click
        tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun validateInputs(name: String, phone: String, password: String, confirmPassword: String): Boolean {
        // Check if any field is empty
        if (name.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validate phone number (must be exactly 11 digits and numeric)
        if (phone.length != 11 || !phone.all { it.isDigit() }) {
            Toast.makeText(this, "Phone number must be 11 digits", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validate password (must be exactly 5 digits and numeric)
        if (password.length != 5 || !password.all { it.isDigit() }) {
            Toast.makeText(this, "Password must be 5 digits", Toast.LENGTH_SHORT).show()
            return false
        }

        // Check if passwords match
        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun saveUserData(name: String, phone: String, password: String) {
        // Save user data in SharedPreferences
        val sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Name", name)
        editor.putString("Phone", phone)
        editor.putString("Password", password)
        editor.apply()
    }
}