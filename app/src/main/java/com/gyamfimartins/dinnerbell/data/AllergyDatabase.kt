package com.gyamfimartins.dinnerbell.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Allergy::class], version = 1,exportSchema = true)
abstract class AllergyDatabase : RoomDatabase() {
    abstract fun allergyDao(): AllergyDao

    companion object {
        @Volatile
        private var INSTANCE: AllergyDatabase? = null

        fun getDatabase(context: Context): AllergyDatabase{
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AllergyDatabase::class.java,
                    "allergy_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}