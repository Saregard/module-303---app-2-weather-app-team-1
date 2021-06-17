package com.example.firstactivity.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.firstactivity.R
import com.example.firstactivity.databinding.ActivitySecondScreenBinding
import com.example.firstactivity.model.City
import com.example.firstactivity.model.Sys
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.roundToInt

class SecondScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDateAndTime()

        val city = intent.getParcelableExtra<City>("cityName")

        if (city != null && isCityValid(city)) {
            displayData(city)
            sunriseAndSunset(city.sys)
        } else {
            displayErrorScreen()
        }
    }

    private fun isCityValid(city: City): Boolean {
        val listOfValidCities = listOf("panama", "gothenburg", "oslo", "london")

        return city.name != null && city.name.lowercase() in listOfValidCities
    }

    private fun displayErrorScreen() {
        binding.weatherIcon.setImageResource(R.drawable.sad_cloud)
        binding.date.visibility = View.INVISIBLE
        binding.time.visibility = View.INVISIBLE
        binding.temperature.visibility = View.INVISIBLE
        binding.cityName.visibility = View.INVISIBLE
    }

    private fun setDateAndTime() {
        val currentDate = Date()

        val timeToShow = SimpleDateFormat("h:mma").format(currentDate)
        binding.time.text = timeToShow
        val dateToShow = SimpleDateFormat("EEEE, d MMMM").format(currentDate)
        binding.date.text = dateToShow
    }

    private fun sunriseAndSunset(sun: Sys?) {
        sun?.let {
            val timeFormat = SimpleDateFormat("HH:mm")
            val sunriseTime = it.sunrise ?: 0
            val sunriseTimeFormated = Date(sunriseTime.toLong() * 1000L)
            binding.layoutSunrise.layoutSymbolIcon.setImageResource(R.drawable.icons_sunrise)
            binding.layoutSunrise.layoutSymbolText.text = timeFormat.format(sunriseTimeFormated)

            val sunsetTime = it.sunset ?: 0
            val sunsetTimeFormated = Date(sunsetTime.toLong() * 1000L)
            binding.layoutSunset.layoutSymbolIcon.setImageResource(R.drawable.icons_sunset)
            binding.layoutSunset.layoutSymbolText.text = timeFormat.format(sunsetTimeFormated)
        }
    }

    private fun displayData(city: City) {
        binding.layoutWind.layoutSymbolIcon.setImageResource(R.drawable.wind)
        binding.layoutWind.layoutSymbolText.text = city.wind?.speed.toString() + "m/s"
        binding.cityName.text = city.name
        binding.temperature.text = city.main?.temp?.roundToInt().toString() + "°"

        if (city.weather?.first()?.description.toString().contains("cloud")) {
            binding.weatherIcon.setImageResource(R.drawable.clouds)
            binding.root.setBackgroundResource(R.drawable.cloudy_background)
        } else if (city.weather?.first()?.description.toString().contains("rain")) {
            binding.weatherIcon.setImageResource(R.drawable.littlerain)
            binding.root.setBackgroundResource(R.drawable.rainy_background)
        } else {
            binding.weatherIcon.setImageResource(R.drawable.sun)
            binding.root.setBackgroundResource(R.drawable.sunny_background)
        }
    }
}
