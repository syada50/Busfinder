package com.example.busfinder


import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class SearchActivity : AppCompatActivity() {

    private lateinit var etDeparture: EditText
    private lateinit var etDestination: EditText
    private lateinit var lvStations: ListView

    private val stations = listOf(
        "Action Paribahan", "Denna Staff Quarter", "Active Paribahan", "Sha Masjid",
        "Abdullahpur", "Aqradut", "Savar", "Notun Bazar", "Airport", "Bangabandhu Avenue",
        "Transport", "Fulbaria", "Azmeri Glory", "Sadarghat", "Chandra"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        etDeparture = findViewById(R.id.etDeparture)
        etDestination = findViewById(R.id.etDestination)
        lvStations = findViewById(R.id.lvStations)

        // Set up stations list
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stations)
        lvStations.adapter = adapter

        // Handle station clicks
        lvStations.setOnItemClickListener { _, _, position, _ ->
            val selectedStation = stations[position]
            etDeparture.setText(selectedStation)
        }
    }
}