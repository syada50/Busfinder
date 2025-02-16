package com.example.busfinder

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment

class BusFragment : Fragment() {

    private lateinit var lvBuses: ListView

    // List of buses in English and Bangla
    private val englishBusList = listOf(
        "Achim Paribahan: Gabtoli to Demra Staff Quarter",
        "Active Paribahan: Shia Masjid to Abdullahpur",
        "Agradut: Savar to Notun Bazar",
        "Airport Bangabandhu Avenue Transport: Fulbaria to Abdullahpur",
        "Azmeri Glory: Sadarghat to Chandra",
        "Ajmi: Dhamrai to Chittagong Road"
    )

    private val banglaBusList = listOf(
        "আচিম পরিবহন: গাবতলী থেকে ডেমরা স্টাফ কোয়ার্টার",
        "অ্যাকটিভ পরিবহন: শিয়া মসজিদ থেকে আব্দুল্লাহপুর",
        "অগ্রদূত: সাভার থেকে নতুন বাজার",
        "এয়ারপোর্ট বঙ্গবন্ধু এভিনিউ পরিবহন: ফুলবাড়িয়া থেকে আব্দুল্লাহপুর",
        "আজমেরী গ্লোরি: সদরঘাট থেকে চন্দ্রা",
        "আজমি: ধামরাই থেকে চট্টগ্রাম রোড"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bus, container, false)

        // Initialize ListView
        lvBuses = view.findViewById(R.id.lvBuses)

        // Get the current language from SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("Language", LanguageManager.ENGLISH) ?: LanguageManager.ENGLISH

        // Set up adapter based on the selected language
        val busList = if (language == LanguageManager.BANGLA) banglaBusList else englishBusList
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, busList)
        lvBuses.adapter = adapter

        // Handle item clicks
        lvBuses.setOnItemClickListener { _, _, position, _ ->
            val selectedBus = busList[position]
            Toast.makeText(requireContext(), "Selected: $selectedBus", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
