package com.kryptopass.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kryptopass.asteroidradar.database.daos.ImageDao
import com.kryptopass.asteroidradar.database.entities.DatabaseImageOfDay

@Database(entities = [DatabaseImageOfDay::class], version = 2, exportSchema = false)
abstract class ImageDatabase : RoomDatabase() {

    abstract val imageDao: ImageDao

    companion object {
        @Volatile
        private var Instance: ImageDatabase? = null

        fun getImageDatabaseInstance(context: Context): ImageDatabase {
            synchronized(this)
            {
                var instance = Instance
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ImageDatabase::class.java, "imageofday"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    Instance = instance
                }
                return instance
            }
        }
    }
}
