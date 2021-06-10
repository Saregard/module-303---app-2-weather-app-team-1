package com.example.firstactivity.backend

import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {

    @GET("data/2.5/weather?q={cityname}&appid={APIkey}")
    fun getWeatherData(@Query("cityname") city: String, @Query("APIkey") API: String)
}