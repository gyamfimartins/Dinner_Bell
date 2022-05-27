package com.gyamfimartins.dinnerbell.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gyamfimartins.dinnerbell.data.Ingredient
import com.gyamfimartins.dinnerbell.data.MealDetail
import com.retrofitcoroutines.example.remote.MealService
import kotlinx.coroutines.*

class IngredientViewModel: ViewModel() {

    private val _ingredient: MutableLiveData<List<Ingredient>> = MutableLiveData()
    val ingredient: LiveData<List<Ingredient>>
        get() = _ingredient

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?>
        get() = _errorMessage


    fun refresh() {
        getIngredient()
    }

    fun getIngredient() {
        viewModelScope.launch {
            _errorMessage.value = null
            _isLoading.value = true
            try {
                val fetchedPosts = MealService().getMealService().getIngredients()
                Log.i(TAG, "Ingredient: $fetchedPosts")
                val currentPosts = _ingredient.value ?: emptyList()
                _ingredient.value = currentPosts + fetchedPosts.ingredientList
            } catch (e: Exception) {
                _errorMessage.value = e.message
                Log.e(TAG, "Exception $e")
            } finally {
                _isLoading.value = false
            }
        }
    }

}