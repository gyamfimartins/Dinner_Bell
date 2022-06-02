package com.gyamfimartins.dinnerbell.data

import androidx.lifecycle.LiveData

// Meal Repository abstracts access to multiple data sources. However this is not the part of the Architecture Component libraries.

class MealRepository(private val mealDao: MealDao) {
    val readAllData: LiveData<List<SavedMeal>> = mealDao.getAllData()

    suspend fun addUser(savedMeal: SavedMeal) {
        mealDao.addMeal(savedMeal)
    }

    suspend fun deleteUser(savedMeal: SavedMeal) {
        mealDao.deleteMeal(savedMeal)
    }

}