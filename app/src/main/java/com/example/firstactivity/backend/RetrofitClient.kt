package com.example.firstactivity.backend

object RetrofitClient {

    private const val BASE_URL = "api.openweathermap.org/"

    val instance: Endpoint by lazy {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        retrofit.create(Endpoint::class.java)
    }
}