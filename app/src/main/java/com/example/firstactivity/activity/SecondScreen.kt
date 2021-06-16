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

class SecondScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding
    private var imm: InputMethodManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showCityName()
        setDateAndTime()
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
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

    private fun showCityName() {
        val currentCity = City()
        binding.CityName.text = currentCity.toString()
    }

    private fun getCityWeather(city: String) {
        RetrofitClient
            .instance
            .getWeatherData(city, "0a902a2e2de35215b6399eb1b6793162")
            .enqueue(object : Callback<City>{
                override fun onResponse(call: Call<City>, response: Response<City>) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.CityName.text.toString()
                    if (response.isSuccessful){
                        val weatherList = response.body()
                        weatherList.let {
                            if (it?.weather?.first()?.description.toString().contains("cloud")) {
                                binding.weathericon.setImageResource(R.drawable.clouds)
                            }else if (it?.weather?.first()?.description.toString().contains("rain")){
                                binding.weathericon.setImageResource(R.drawable.littlerain)
                            }else {
                                binding.weathericon.setImageResource(R.drawable.sun)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<City>, t: Throwable) {
                    Log.e(TAG, "Error getting city: ${t.localizedMessage}")
                    Toast.makeText(this@SecondScreen,
                        R.string.unable_to_get_city,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }
}