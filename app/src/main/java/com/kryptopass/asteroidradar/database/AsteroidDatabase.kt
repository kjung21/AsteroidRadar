package com.kryptopass.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kryptopass.asteroidradar.database.daos.AsteroidDao
import com.kryptopass.asteroidradar.database.entities.DatabaseAsteroid

@Database(entities = [DatabaseAsteroid::class], version = 2, exportSchema = false)
abstract class AsteroidDatabase : RoomDatabase() {

    abstract val asteroidDao: AsteroidDao

    companion object {
        @Volatile
        private var Instance: AsteroidDatabase? = null

        fun getAsteroidDatabaseInstance(context: Context): AsteroidDatabase {
            synchronized(this)
            {
                var instance = Instance
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidDatabase::class.java, "asteroids"
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
