package com.example.busfinder

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.busfinder.R.id
import com.example.busfinder.R.id.rbEnglish
import com.example.busfinder.R.id.rgLanguage

class MoreFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_more, container, false)

        // Initialize SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

        // Get references to UI elements
        val rgLanguage = view.findViewById<RadioGroup>(rgLanguage)
        val rbEnglish = view.findViewById<RadioButton>(rbEnglish)
        val rbBangla = view.findViewById<RadioButton>(R.id.rbBangla)
        val btnContactSupport = view.findViewById<Button>(R.id.btnContactSupport)
        val btnSubmitFeedback = view.findViewById<Button>(R.id.btnSubmitFeedback)
        val btnRateApp = view.findViewById<Button>(R.id.btnRateApp)
        val btnMore = view.findViewById<Button>(R.id.btnMore)

        // Set the default language based on the saved preference
        val currentLanguage = sharedPreferences.getString("Language", LanguageManager.ENGLISH) ?: LanguageManager.ENGLISH
        if (currentLanguage == LanguageManager.BANGLA) {
            rbBangla.isChecked = true
        } else {
            rbEnglish.isChecked = true
        }

        // Update UI strings based on the current language
        updateUIStrings(view, currentLanguage)

        // Handle language change
        rgLanguage.setOnCheckedChangeListener { _, checkedId ->
            val selectedLanguage = when (checkedId) {
                R.id.rbEnglish -> LanguageManager.ENGLISH
                R.id.rbBangla -> LanguageManager.BANGLA
                else -> LanguageManager.ENGLISH
            }
            sharedPreferences.edit().putString("Language", selectedLanguage).apply()
            updateUIStrings(view, selectedLanguage)
            Toast.makeText(requireContext(), "Language changed to ${if (selectedLanguage == LanguageManager.ENGLISH) "English" else "Bangla"}", Toast.LENGTH_SHORT).show()
        }

        // Handle contact support
        btnContactSupport.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:syada.amad-04-09@dipti.com.bd")
            }
            startActivity(emailIntent)
        }

        // Handle submit feedback
        btnSubmitFeedback.setOnClickListener {
            Toast.makeText(requireContext(), LanguageManager.getString("submit_feedback", currentLanguage), Toast.LENGTH_SHORT).show()
        }

        // Handle rate app
        btnRateApp.setOnClickListener {
            val playStoreIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("market://details?id=com.example.busfinder")
            }
            startActivity(playStoreIntent)
        }

        // Handle more options
        btnMore.setOnClickListener {
            Toast.makeText(requireContext(), LanguageManager.getString("more", currentLanguage), Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun updateUIStrings(view: View, language: String) {
        view.findViewById<TextView>(R.id.tvMoreTitle).text = LanguageManager.getString("more", language)
        view.findViewById<TextView>(R.id.tvSettings).text = LanguageManager.getString("settings", language)
        view.findViewById<Button>(R.id.btnContactSupport).text = LanguageManager.getString("contact_support", language)
        view.findViewById<Button>(R.id.btnSubmitFeedback).text = LanguageManager.getString("submit_feedback", language)
        view.findViewById<Button>(R.id.btnRateApp).text = LanguageManager.getString("rate_app", language)
        view.findViewById<Button>(R.id.btnMore).text = LanguageManager.getString("more", language)
    }
}