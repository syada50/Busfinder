package com.example.busfinder





import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more)

        val cbLanguage = findViewById<CheckBox>(R.id.cbLanguage)
        val btnContactSupport = findViewById<Button>(R.id.btnContactSupport)
        val btnSubmitFeedback = findViewById<Button>(R.id.btnSubmitFeedback)
        val btnRateApp = findViewById<Button>(R.id.btnRateApp)
        val btnMore = findViewById<Button>(R.id.btnMore)

        // Handle language change
        cbLanguage.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Language Changed", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle contact support
        btnContactSupport.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:support@busfinder.com")
            }
            startActivity(emailIntent)
        }

        // Handle submit feedback
        btnSubmitFeedback.setOnClickListener {
            Toast.makeText(this, "Feedback Submitted", Toast.LENGTH_SHORT).show()
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
            Toast.makeText(this, "More Options Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}