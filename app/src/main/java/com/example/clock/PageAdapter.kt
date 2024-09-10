package com.example.clock

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3 // Atualize o número de fragmentos

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TimerFragment()
            1 -> StopwatchFragment()
            2 -> SettingsFragment() // Adicione o fragmento de configurações
            else -> throw IllegalStateException("Invalid position $position")
        }
    }
}
