package com.gyamfimartins.dinnerbell.data

import com.google.gson.annotations.SerializedName

data class AreaList(@SerializedName("meals") val mList : List<Area>)
data class Area(val strArea: String)

data class CategoryList(@SerializedName("meals") val categoryList : List<Category>)
data class Category(val strCategory: String)

data class MealList(@SerializedName("meals") val mealList : List<Meal>)
data class Meal(val strMeal: String, val idMeal: String, val strMealThumb: String)