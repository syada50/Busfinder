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

class SearchFragment : Fragment() {

    private lateinit var lvStations: ListView

    // List of stations in English and Bangla
    private val englishStations = listOf(
        "Abdullahpur", "Airport", "Azmeri", "Bangabandhu Avenue", "Chandra", "Denna Staff Quarter",
        "Fulbaria", "Notun Bazar", "Savar", "Sha Masjid", "Sadarghat", "Action Paribahan"
    )

    private val banglaStations = listOf(
        "আব্দুল্লাহপুর", "এয়ারপোর্ট", "আজমেরী", "বঙ্গবন্ধু এভিনিউ", "চন্দ্রা", "ডেননা স্টাফ কোয়ার্টার",
        "ফুলবাড়িয়া", "নতুন বাজার", "সাভার", "শাহ মসজিদ", "সদরঘাট", "অ্যাকশন পরিবহন"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        // Initialize ListView
        lvStations = view.findViewById(R.id.lvStations)

        // Get the current language from SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("Language", LanguageManager.ENGLISH) ?: LanguageManager.ENGLISH

        // Set up adapter based on the selected language
        val stations = if (language == LanguageManager.BANGLA) banglaStations else englishStations
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, stations)
        lvStations.adapter = adapter

        // Handle item clicks
        lvStations.setOnItemClickListener { _, _, position, _ ->
            val selectedStation = stations[position]
            Toast.makeText(requireContext(), "Selected: $selectedStation", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}