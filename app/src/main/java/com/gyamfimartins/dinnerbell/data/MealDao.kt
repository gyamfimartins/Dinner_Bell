package com.gyamfimartins.dinnerbell.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // <- Annotate the 'addUser' function below. Set the onConflict strategy to IGNORE so if exactly the same user exists, it will just ignore it.
    suspend fun addMeal(savedMeal: SavedMeal)

    @Delete
    suspend fun deleteMeal(savedMeal: SavedMeal)


    @Query("SELECT * from meal_table ORDER BY id ASC") // <- Add a query to fetch all users (in user_table) in ascending order by their IDs.
    fun getAllData(): LiveData<List<SavedMeal>> // <- This means function return type is List. Specifically, a List of Users.
}