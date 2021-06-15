package com.example.firstactivity.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.firstactivity.R
import java.text.SimpleDateFormat
import java.util.*

class SecondScreen : AppCompatActivity() {
    private var textViewDate: TextView? = null
    private var textViewTime: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)
    }
    private fun setDateAndTime() {
        textViewDate = findViewById(R.id.Date)
        textViewTime = findViewById(R.id.Time)
        val currentDate = Date()
        val timeFormat = SimpleDateFormat("hh:mm a")
        val dateFormat = SimpleDateFormat("dd MMMM yyyy")
        
    }

}