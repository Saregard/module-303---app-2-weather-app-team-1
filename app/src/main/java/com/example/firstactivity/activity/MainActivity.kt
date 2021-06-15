package com.example.firstactivity.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.example.firstactivity.R

class MainActivity : AppCompatActivity(), TextView.OnEditorActionListener {

    private var editTextCity: EditText? = null
    private var progressBar: ProgressBar? = null
    private var imm: InputMethodManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextCity = findViewById(R.id.editText_enter_city)
        progressBar = findViewById(R.id.progress_bar)
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        editTextCity?.setOnEditorActionListener(this)

    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            return if (v == editTextCity) {
                val city = editTextCity?.text?.trim().toString()

                if (city.isBlank() or city.isEmpty()) {
                    editTextCity?.error = getString(R.string.you_need_to_enter_a_city)
                } else {
                    imm?.hideSoftInputFromWindow(editTextCity?.windowToken, 0)
                    progressBar?.visibility = View.VISIBLE
                    getWeatherForCity(city)
                    moveToNextActivity(city)
                }
                true
            } else
                false
    }

    private fun getWeatherForCity(city: String) {
    }
    private fun moveToNextActivity(cityToLookWeatherInfo: String) {
        val intent = Intent(this@MainActivity, SecondScreen::class.java)
        intent.putExtra("cityName", cityToLookWeatherInfo)
        startActivity(intent)
    }

}