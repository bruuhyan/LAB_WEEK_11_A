package com.example.lab_week_11_a

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.widget.TextView
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ⬇⬇⬇ TAMBAHKAN DI SINI ⬇⬇⬇
        val settingsStore = (application as SettingsApplication).settingsStore

        val settingsViewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SettingsViewModel(settingsStore) as T
                }
            }
        )[SettingsViewModel::class.java]

        // Observe data
        settingsViewModel.textLiveData.observe(this) { value ->
            findViewById<TextView>(R.id.activity_main_text_view).text = value
        }

        // Tombol save
        findViewById<Button>(R.id.activity_main_button).setOnClickListener {
            val input = findViewById<EditText>(R.id.activity_main_edit_text).text.toString()
            settingsViewModel.saveText(input)
        }
        // ⬆⬆⬆ SAMPAI SINI ⬆⬆⬆
    }
}

