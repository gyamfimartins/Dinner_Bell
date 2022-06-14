package com.gyamfimartins.dinnerbell.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gyamfimartins.dinnerbell.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllergyViewModel(application: Application): AndroidViewModel(application) {
    val getAllAllergy: LiveData<List<Allergy>>
    private val repository: AllergyRepository

    init {
        val allergyDao = AllergyDatabase.getDatabase(application).allergyDao()
        repository= AllergyRepository(allergyDao)
        getAllAllergy = repository.getAllAllergy
    }

    fun addAllergy(allergy: Allergy) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAllergy(allergy)
        }
    }

    fun deleteAllergy(allergy: Allergy) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllergy(allergy)
        }
    }
}