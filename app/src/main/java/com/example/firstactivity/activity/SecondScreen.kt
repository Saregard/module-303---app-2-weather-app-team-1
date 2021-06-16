package com.example.firstactivity.activity

import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.firstactivity.R
import com.example.firstactivity.backend.RetrofitClient
import com.example.firstactivity.databinding.ActivitySecondScreenBinding
import com.example.firstactivity.model.City
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class SecondScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDateAndTime()

        val city = intent.getParcelableExtra<City>("cityName")

        if (city != null) {
            displayData(city)
        } else {
            displayErrorScreen()
        }

    }

    private fun displayErrorScreen() {

    }

    private fun setDateAndTime() {
        val currentDate = Date()

        val timeToShow = SimpleDateFormat("h:mma").format(currentDate)
        binding.Time.text = timeToShow
        val dateToShow = SimpleDateFormat("EEEE, d MMMM").format(currentDate)
        binding.Date.text = dateToShow
    }


    private fun displayData(city: City) {
        binding.CityName.text = city.name
        binding.temperature.text = city.main?.temp?.roundToInt().toString() + "°"
        if (city.weather?.first()?.description.toString().contains("cloud")) {
            binding.weathericon.setImageResource(R.drawable.clouds)
            binding.root.setBackgroundResource(R.drawable.cloudy_background)
        } else if (city.weather?.first()?.description.toString().contains("rain")) {
            binding.weathericon.setImageResource(R.drawable.littlerain)
            binding.root.setBackgroundResource(R.drawable.rainy_background)
        } else {
            binding.weathericon.setImageResource(R.drawable.sun)
            binding.root.setBackgroundResource(R.drawable.sunny_background)
        }
    }
}