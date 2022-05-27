package com.gyamfimartins.dinnerbell.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gyamfimartins.dinnerbell.data.Meal
import com.gyamfimartins.dinnerbell.data.MealList
import com.retrofitcoroutines.example.remote.MealService
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback

class MealViewModel: ViewModel() {

    private val _mealList: MutableLiveData<List<Meal>> = MutableLiveData()
    val mealList: LiveData<List<Meal>>
        get() = _mealList

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?>
        get() = _errorMessage


    fun refresh(query: String, screen: String) {
        if(screen.equals("Home"))
            getMealListByArea(query)
        else
            getMealListByCategory(query)

    }

    fun getMealListByArea(query: String) {
        viewModelScope.launch {
            Log.i(TAG, "Query $query")
            _errorMessage.value = null
            _isLoading.value = true
            try {
                val fetchedPosts = MealService().getMealService().getMealByArea(query)
                Log.i(TAG, "Meals: $fetchedPosts")
                val currentPosts = _mealList.value ?: emptyList()
                _mealList.value = currentPosts + fetchedPosts.mealList
            } catch (e: Exception) {
                _errorMessage.value = e.message
                Log.e(TAG, "Exception $e")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getMealListByCategory(query: String) {
        viewModelScope.launch {
            Log.i(TAG, "Query $query")
            _errorMessage.value = null
            _isLoading.value = true
            try {
                val fetchedPosts = MealService().getMealService().getMealByCategory(query)
                Log.i(TAG, "Meals: $fetchedPosts")
                val currentPosts = _mealList.value ?: emptyList()
                _mealList.value = currentPosts + fetchedPosts.mealList
            } catch (e: Exception) {
                _errorMessage.value = e.message
                Log.e(TAG, "Exception $e")
            } finally {
                _isLoading.value = false
            }
        }
    }

}