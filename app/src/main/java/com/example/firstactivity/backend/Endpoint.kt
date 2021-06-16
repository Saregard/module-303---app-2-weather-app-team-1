package com.example.firstactivity.backend

import com.example.firstactivity.model.City
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("data/2.5/weather")
    fun getWeatherData(
        @Query("q") q: String,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): Call<City>
}
