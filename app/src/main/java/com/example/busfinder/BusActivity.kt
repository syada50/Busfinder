package com.example.busfinder

import android.widget.Toast
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class BusActivity : AppCompatActivity() {

    private lateinit var etBusName: EditText
    private lateinit var lvBuses: ListView

    private val busList = listOf(
        "Achim Paribahan: Gabtoli to Demra Staff Quarter",
        "Active Paribahan: Shia Masjid to Abdullahpur",
        "Agradut: Savar to Notun Bazar",
        "Airport Bangabandhu Avenue Transport: Fulbaria to Abdullahpur",
        "Azmeri Glory: Sadarghat to Chandra",
        "Ajmi: Dhamrai to Chittagong Road"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus)

        etBusName = findViewById(R.id.etBusName)
        lvBuses = findViewById(R.id.lvBuses)

        // Set up bus list
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, busList)
        lvBuses.adapter = adapter

        // Handle bus clicks
        lvBuses.setOnItemClickListener { _, _, position, _ ->
            val selectedBus = busList[position]
            // Show details or navigate to another activity
            Toast.makeText(this, "Selected: $selectedBus", Toast.LENGTH_SHORT).show()
        }
    }
}