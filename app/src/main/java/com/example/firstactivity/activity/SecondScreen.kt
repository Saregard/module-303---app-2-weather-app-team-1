package com.example.firstactivity.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.firstactivity.R
import com.example.firstactivity.backend.RetrofitClient
import com.example.firstactivity.databinding.ActivitySecondScreenBinding
import com.example.firstactivity.model.City
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class SecondScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDateAndTime()

        val city = intent.getStringExtra("cityName")
        getCityWeather(city.toString())
    }
    private fun setDateAndTime() {
        val currentDate = Date()

        val timeToShow = SimpleDateFormat("hh:mm a").format(currentDate)
        binding.Time.text = timeToShow

        val dateToShow = SimpleDateFormat("dd MMMM yyyy").format(currentDate)
        binding.Date.text = dateToShow
    }

    private fun getCityWeather(city: String) {
        RetrofitClient
            .instance
            .getWeatherData(city, "0a902a2e2de35215b6399eb1b6793162")
            .enqueue(object : Callback<City>{
                override fun onResponse(call: Call<City>, response: Response<City>) {
                    binding.progressBar.visibility = View.VISIBLE
                    if (){

                    }
                    // Get the right weather icon

                    //Display weather in the city + icon
                }

                override fun onFailure(call: Call<City>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        // Get current weather in the city
    }
}