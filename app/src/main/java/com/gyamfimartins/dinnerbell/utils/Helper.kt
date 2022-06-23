package com.gyamfimartins.dinnerbell.utils

import com.gyamfimartins.dinnerbell.data.Allergy
import com.gyamfimartins.dinnerbell.data.Meal

fun filterMealList(mealList: List<Meal>, allergyList: List<Allergy>): List<Meal>{
   return allergyList.flatMap { allergy -> mealList.filterNot { allergy.strIngredient == it.strMeal }}
}