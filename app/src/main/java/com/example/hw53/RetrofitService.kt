package com.example.hw53

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitService {
    companion object{
        val retrofit = Retrofit.Builder().baseUrl("https://pixabay.com").
        addConverterFactory(GsonConverterFactory.create()).build()

        var api = retrofit.create(PixaApi::class.java)
    }

}