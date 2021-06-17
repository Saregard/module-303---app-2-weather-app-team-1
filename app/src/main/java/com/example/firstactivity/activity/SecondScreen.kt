package com.example.firstactivity.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.firstactivity.R
import com.example.firstactivity.databinding.ActivitySecondScreenBinding
import com.example.firstactivity.model.City
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
        } else {
            displayErrorScreen()
        }
    }

    private fun isCityValid(city: City): Boolean{
        val listOfValidCities = listOf("panama","gothenburg", "oslo", "london")

        return city.name != null && city.name.lowercase() in listOfValidCities
    }

    private fun displayErrorScreen() {
        binding.weathericon.setImageResource(R.drawable.sad_cloud)
        binding.Date.visibility = View.INVISIBLE
        binding.Time.visibility = View.INVISIBLE
        binding.temperature.visibility = View.INVISIBLE
        binding.CityName.visibility = View.INVISIBLE
    }

    private fun setDateAndTime() {
        val currentDate = Date()

        val timeToShow = SimpleDateFormat("h:mma").format(currentDate)
        binding.Time.text = timeToShow
        val dateToShow = SimpleDateFormat("EEEE, d MMMM").format(currentDate)
        binding.Date.text = dateToShow
    }

    private fun displayData(city: City) {
        binding.windicon.setImageResource(R.drawable.wind)
        binding.windspeed.text = city.wind?.speed.toString() + "m/s"
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
