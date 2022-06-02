package com.gyamfimartins.dinnerbell.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gyamfimartins.dinnerbell.data.MealDatabase
import com.gyamfimartins.dinnerbell.data.MealRepository
import com.gyamfimartins.dinnerbell.data.SavedMeal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// mealViewModel provides users data to the UI and survive configuration changes.
// A ViewModel acts as a communication center between the Repository and the UI.

class SavedMealViewModel(application: Application): AndroidViewModel(application) {
    val getAllData: LiveData<List<SavedMeal>>
    private val repository: MealRepository

    init {
        val userDao = MealDatabase.getDatabase(application).mealDao()
        repository= MealRepository(userDao)
        getAllData = repository.readAllData
    }

    fun addMeal(savedMeal: SavedMeal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(savedMeal)
        }
    }

    fun deleteMeal(savedMeal: SavedMeal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(savedMeal)
        }
    }
}