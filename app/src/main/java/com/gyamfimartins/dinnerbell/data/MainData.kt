package com.gyamfimartins.dinnerbell.data

import com.google.gson.annotations.SerializedName

data class AreaList(@SerializedName("meals") val mList : List<Area>)
data class Area(val strArea: String)

data class CategoryList(@SerializedName("meals") val categoryList : List<Category>)
data class Category(val strCategory: String)

data class MealList(@SerializedName("meals") val mealList : List<Meal>)
data class Meal(val strMeal: String, val idMeal: String, val strMealThumb: String)

data class IngredientList(@SerializedName("meals") val ingredientList : List<Ingredient>)
data class Ingredient(val strIngredient: String, val strDescription: String)

data class MealDetailList(@SerializedName("meals") val mealDetailList : List<MealDetail>)
data class MealDetail(
    var idMeal: String,
    var strMeal: String,
    var strCategory: String,
    var strArea: String,
    var strInstructions: String,
    var strMealThumb: String,
    var strTags: String,
    var strYoutube: String,
    var strIngredient1: String,
    var strIngredient2: String,
    var strIngredient3: String,
    var strIngredient4: String,
    var strIngredient5: String,
    var strIngredient6: String,
    var strIngredient7: String,
    var strIngredient8: String,
    var strIngredient9: String,
    var strIngredient10: String,
    var strIngredient11: String,
    var strIngredient12: String,
    var strIngredient13: String,
    var strIngredient14: String,
    var strIngredient15: String,
    var strMeasure1: String,
    var strMeasure2: String,
    var strMeasure3: String,
    var strMeasure4: String,
    var strMeasure5: String,
    var strMeasure6: String,
    var strMeasure7: String,
    var strMeasure8: String,
    var strMeasure9: String,
    var strMeasure10: String,
    var strMeasure11: String,
    var strMeasure12: String,
    var strMeasure13: String,
    var strMeasure14: String,
    var strMeasure15: String
)