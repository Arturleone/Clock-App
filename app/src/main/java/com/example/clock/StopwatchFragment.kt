package com.example.clock

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class StopwatchFragment : Fragment(R.layout.fragment_stopwatch) {

    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var timeDisplay: TextView

    private var handler: Handler? = null
    private var startTime: Long = 0
    private var isStopwatchRunning = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startButton = view.findViewById(R.id.startButtonStopwatch)
        stopButton = view.findViewById(R.id.stopButtonStopwatch)
        timeDisplay = view.findViewById(R.id.timeDisplayStopwatch)

        startButton.setOnClickListener {
            if (!isStopwatchRunning) {
                startStopwatch()
            }
        }

        stopButton.setOnClickListener {
            stopStopwatch()
        }
    }

    private fun startStopwatch() {
        handler = Handler(Looper.getMainLooper())
        startTime = System.currentTimeMillis()

        val runnable = object : Runnable {
            override fun run() {
                val elapsedMillis = System.currentTimeMillis() - startTime
                val hours = elapsedMillis / (1000 * 60 * 60)
                val minutes = (elapsedMillis / (1000 * 60)) % 60
                val seconds = (elapsedMillis / 1000) % 60
                timeDisplay.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
                handler?.postDelayed(this, 1000)
            }
        }

        handler?.post(runnable)
        isStopwatchRunning = true
    }

    private fun stopStopwatch() {
        handler?.removeCallbacksAndMessages(null)
        timeDisplay.text = "00:00:00"
        isStopwatchRunning = false
    }
}
