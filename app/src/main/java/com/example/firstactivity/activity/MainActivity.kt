package com.example.firstactivity.activity

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firstactivity.R
import com.example.firstactivity.backend.RetrofitClient
import com.example.firstactivity.databinding.ActivityMainBinding
import com.example.firstactivity.model.City
import kotlinx.android.synthetic.main.activity_second_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), TextView.OnEditorActionListener {

    private lateinit var binding: ActivityMainBinding
    private var imm: InputMethodManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        binding.editTextEnterCity.setOnEditorActionListener(this)
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        return if (v == binding.editTextEnterCity) {
            val city = binding.editTextEnterCity.text?.trim().toString()

            if (city.isBlank() or city.isEmpty()) {
                binding.editTextEnterCity.error = getString(R.string.you_need_to_enter_a_city)
            } else {
                imm?.hideSoftInputFromWindow(binding.editTextEnterCity.windowToken, 0)
                binding.progressBar.visibility = View.VISIBLE
                getCityWeather(city)
            }
            true
        } else
            false
    }

    private fun moveToNextActivity(cityToLookWeatherInfo: City?) {
        val intent = Intent(this@MainActivity, SecondScreen::class.java)
        intent.putExtra("cityName", cityToLookWeatherInfo)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        binding.editTextEnterCity.setText("")
    }

    private fun getCityWeather(city: String) {
        RetrofitClient
            .instance
            .getWeatherData(city, "0a902a2e2de35215b6399eb1b6793162", "metric")
            .enqueue(object : Callback<City> {
                override fun onResponse(call: Call<City>, response: Response<City>) {
                    binding.progressBar.visibility = View.INVISIBLE
                    if (response.isSuccessful) {
                        val weatherInfo = response.body()
                        moveToNextActivity(weatherInfo)
                    } else {
                        binding.editTextEnterCity.error = getString(R.string.that_is_not_a_city)
                    }
                }

                override fun onFailure(call: Call<City>, t: Throwable) {
                    Log.e(ContentValues.TAG, "Error getting city: ${t.localizedMessage}")
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(
                        this@MainActivity,
                        R.string.unable_to_get_city,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }
}
