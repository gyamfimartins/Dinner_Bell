package com.gyamfimartins.dinnerbell.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gyamfimartins.dinnerbell.data.Area
import com.gyamfimartins.dinnerbell.data.AreaList
import com.gyamfimartins.dinnerbell.data.Category
import com.retrofitcoroutines.example.remote.MealService
import kotlinx.coroutines.*

class CategoryViewModel: ViewModel() {

    val mealService = MealService().getMealService()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    val categoryList = MutableLiveData<List<Category>>()
    val categoryLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCategory()
    }

    private fun fetchCategory() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mealService.getCategoryList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    categoryList.value = response.body()?.categoryList
                    categoryLoadError.value = null
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
        categoryLoadError.value = ""
        loading.value = false
    }

    private fun onError(message: String) {
        categoryLoadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}