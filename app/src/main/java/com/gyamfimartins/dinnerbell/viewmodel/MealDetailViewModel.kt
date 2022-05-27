package com.gyamfimartins.dinnerbell.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gyamfimartins.dinnerbell.data.Meal
import com.gyamfimartins.dinnerbell.data.MealDetail
import com.gyamfimartins.dinnerbell.data.MealDetailList
import com.gyamfimartins.dinnerbell.data.MealList
import com.retrofitcoroutines.example.remote.MealService
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback

class MealDetailViewModel: ViewModel() {

    private val _mealDetailList: MutableLiveData<List<MealDetail>> = MutableLiveData()
    val mealDetailList: LiveData<List<MealDetail>>
        get() = _mealDetailList

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?>
        get() = _errorMessage


    fun refresh(query: String, screen: String) {
        if(screen.equals("Random"))
            getRandom()
        else
            getMealDetail(query)

    }

    fun getMealDetail(query: String) {
        viewModelScope.launch {
            Log.i(TAG, "Query $query")
            _errorMessage.value = null
            _isLoading.value = true
            try {
                val fetchedPosts = MealService().getMealService().getMealDetail(query)
                Log.i(TAG, "Mealsdetail: $fetchedPosts")
                val currentPosts = _mealDetailList.value ?: emptyList()
                _mealDetailList.value = currentPosts + fetchedPosts.mealDetailList
            } catch (e: Exception) {
                _errorMessage.value = e.message
                Log.e(TAG, "Exception $e")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getRandom() {
        viewModelScope.launch {
            _errorMessage.value = null
            _isLoading.value = true
            try {
                val fetchedPosts = MealService().getMealService().getRandomMeal()
                Log.i(TAG, "Meals: $fetchedPosts")
                val currentPosts = _mealDetailList.value ?: emptyList()
                _mealDetailList.value = currentPosts + fetchedPosts.mealDetailList
            } catch (e: Exception) {
                _errorMessage.value = e.message
                Log.e(TAG, "Exception $e")
            } finally {
                _isLoading.value = false
            }
        }
    }

}