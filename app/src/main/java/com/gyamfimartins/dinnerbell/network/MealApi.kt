package com.retrofitcoroutines.example.remote

import com.gyamfimartins.dinnerbell.data.*
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
    suspend fun getMealByArea(@Query("a") area: String): MealList

    @GET("filter.php")
    suspend fun getMealByCategory(@Query("c") area: String): MealList

    @GET("lookup.php")
    suspend fun getMealDetail(@Query("i") mealid: String): MealDetailList

    @GET("random.php")
    suspend fun getRandomMeal(): MealDetailList

    @GET("list.php?i=list")
    suspend fun getIngredients(): IngredientList
}