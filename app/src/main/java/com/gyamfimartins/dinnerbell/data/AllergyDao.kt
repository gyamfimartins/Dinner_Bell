package com.gyamfimartins.dinnerbell.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AllergyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllergy(allergy: Allergy)

    @Delete
    suspend fun deleteAllergy(allergy: Allergy)

    @Query("SELECT * from allergy_table ORDER BY id ASC")
    fun getAllAllergy(): LiveData<List<Allergy>>
}