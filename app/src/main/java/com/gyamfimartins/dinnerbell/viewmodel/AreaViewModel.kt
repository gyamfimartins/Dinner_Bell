package com.gyamfimartins.dinnerbell.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gyamfimartins.dinnerbell.data.Area
import com.gyamfimartins.dinnerbell.data.AreaList
import com.retrofitcoroutines.example.remote.MealService
import kotlinx.coroutines.*

class AreaViewModel: ViewModel() {

    val mealService = MealService().getMealService()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    val areaList = MutableLiveData<List<Area>>()
    val areaLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchArea()
    }

    private fun fetchArea() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mealService.getAreaList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    areaList.value = response.body()?.mList
                    areaLoadError.value = null
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
        areaLoadError.value = ""
        loading.value = false
    }

    private fun onError(message: String) {
        areaLoadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}