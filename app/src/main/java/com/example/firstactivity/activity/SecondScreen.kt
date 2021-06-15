package com.example.firstactivity.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.firstactivity.R
import com.example.firstactivity.databinding.ActivitySecondScreenBinding
import java.text.SimpleDateFormat
import java.util.*

class SecondScreen : AppCompatActivity() {
    lateinit var binding: ActivitySecondScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setDateAndTime()
    }
    private fun setDateAndTime() {
        val currentDate = Date()

        val timeToShow = SimpleDateFormat("hh:mm a").format(currentDate)
        binding.Time.text = timeToShow

        val dateToShow = SimpleDateFormat("dd MMMM yyyy").format(currentDate)
        binding.Date.text = dateToShow
    }

    private fun


}