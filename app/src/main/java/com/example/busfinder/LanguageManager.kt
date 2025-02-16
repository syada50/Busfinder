package com.example.busfinder

object LanguageManager {
    const val ENGLISH = "en"
    const val BANGLA = "bn"

    private val englishStrings = mapOf(
        "app_name" to "Bus Finder",
        "more" to "More",
        "settings" to "Settings",
        "change_language" to "Change Language",
        "contact_support" to "Contact Support",
        "submit_feedback" to "Submit Feedback",
        "rate_app" to "Rate 5 Star"
    )

    private val banglaStrings = mapOf(
        "app_name" to "বাস ফাইন্ডার",
        "more" to "আরও",
        "settings" to "সেটিংস",
        "change_language" to "ভাষা পরিবর্তন করুন",
        "contact_support" to "সাপোর্টে যোগাযোগ করুন",
        "submit_feedback" to "ফিডব্যাক জমা দিন",
        "rate_app" to "৫ স্টার রেট দিন"
    )

    fun getString(key: String, language: String): String {
        return when (language) {
            BANGLA -> banglaStrings[key] ?: key
            else -> englishStrings[key] ?: key
        }
    }
}