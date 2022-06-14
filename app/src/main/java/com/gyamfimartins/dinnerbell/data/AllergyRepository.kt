package com.gyamfimartins.dinnerbell.data

import androidx.lifecycle.LiveData

// Meal Repository abstracts access to multiple data sources. However this is not the part of the Architecture Component libraries.

class AllergyRepository(private val allergyDao: AllergyDao) {
    val getAllAllergy: LiveData<List<Allergy>> = allergyDao.getAllAllergy()

    suspend fun addAllergy(allergy: Allergy) {
        allergyDao.addAllergy(allergy)
    }

    suspend fun deleteAllergy(allergy: Allergy) {
        allergyDao.deleteAllergy(allergy)
    }

}