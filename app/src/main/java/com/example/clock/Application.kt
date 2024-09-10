package com.example.clock

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val isNightMode = sharedPreferences.getBoolean("night_mode", false)
        AppCompatDelegate.setDefaultNightMode(
            if (isNightMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }
}
