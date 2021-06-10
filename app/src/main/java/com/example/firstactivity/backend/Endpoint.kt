package com.example.firstactivity.backend

interface Endpoint {

    @GET("data/2.5/weather?q={city name}&appid={API key}")
    fun getWeatherData(@Path("city name") city: String)
}