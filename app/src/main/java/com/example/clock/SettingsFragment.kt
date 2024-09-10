package com.example.clock

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var nightModeSwitch: CompoundButton
    private var isNightMode = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nightModeSwitch = view.findViewById(R.id.nightModeSwitch)

        // Carrega o modo atual do SharedPreferences
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        isNightMode = sharedPreferences.getBoolean("night_mode", false)
        updateMode()

        nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            isNightMode = isChecked
            updateMode()

            // Salva a configuração no SharedPreferences
            with(sharedPreferences.edit()) {
                putBoolean("night_mode", isNightMode)
                apply()
            }
        }
    }

    private fun updateMode() {
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            nightModeSwitch.text = "Light Mode"
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            nightModeSwitch.text = "Dark Mode"
        }
    }
}