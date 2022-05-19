package com.retrofitcoroutines.example.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealService {
    private val BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    fun getMealService(): MealApi{
       return Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(MealApi::class.java)
    }
}