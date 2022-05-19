package com.retrofitcoroutines.example.remote

import com.gyamfimartins.dinnerbell.data.AreaList
import com.gyamfimartins.dinnerbell.data.CategoryList
import com.gyamfimartins.dinnerbell.data.MealList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("list.php?a=list")
    suspend fun getAreaList(): Response<AreaList>

    @GET("list.php?c=list")
    suspend fun getCategoryList(): Response<CategoryList>

    @GET("filter.php")
    fun getMealByArea(@Query("a") area: String): Response<MealList>
}