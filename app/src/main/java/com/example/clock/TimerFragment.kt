package com.example.clock

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.NumberPicker
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class TimerFragment : Fragment(R.layout.fragment_timer) {

    private lateinit var hourPicker: NumberPicker
    private lateinit var minutePicker: NumberPicker
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var timeDisplay: TextView

    private var isTimerRunning = false
    private var countDownTimer: CountDownTimer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hourPicker = view.findViewById(R.id.hourPickerTimer)
        minutePicker = view.findViewById(R.id.minutePickerTimer)
        startButton = view.findViewById(R.id.startButtonTimer)
        stopButton = view.findViewById(R.id.stopButtonTimer)
        timeDisplay = view.findViewById(R.id.timeDisplayTimer)

        hourPicker.minValue = 0
        hourPicker.maxValue = 23
        minutePicker.minValue = 0
        minutePicker.maxValue = 59

        // Inicialmente, define o valor dos Pickers e a exibição do tempo
        hourPicker.value = 0
        minutePicker.value = 0
        timeDisplay.text = "00:00:00"

        startButton.setOnClickListener {
            if (!isTimerRunning) {
                startTimer()
            }
        }

        stopButton.setOnClickListener {
            stopTimer()
        }

        hourPicker.setOnValueChangedListener { _, _, _ -> resetTimerIfNeeded() }
        minutePicker.setOnValueChangedListener { _, _, _ -> resetTimerIfNeeded() }
    }

    private fun startTimer() {
        val hoursInMillis = hourPicker.value * 60 * 60 * 1000L
        val minutesInMillis = minutePicker.value * 60 * 1000L
        val timeInMillis = hoursInMillis + minutesInMillis

        countDownTimer?.cancel()

        countDownTimer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val hours = millisUntilFinished / (1000 * 60 * 60)
                val minutes = (millisUntilFinished / (1000 * 60)) % 60
                val seconds = (millisUntilFinished / 1000) % 60
                timeDisplay.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            }

            override fun onFinish() {
                timeDisplay.text = "00:00:00"
                isTimerRunning = false
            }
        }
        countDownTimer?.start()
        isTimerRunning = true
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
        timeDisplay.text = "00:00:00"
        isTimerRunning = false
    }

    private fun resetTimerIfNeeded() {
        if (isTimerRunning) {
            stopTimer()
            startTimer()
        }
    }
}
