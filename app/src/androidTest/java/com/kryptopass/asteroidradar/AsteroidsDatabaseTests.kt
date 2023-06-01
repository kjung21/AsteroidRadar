package com.kryptopass.asteroidradar

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.kryptopass.asteroidradar.database.daos.AsteroidDao
import com.kryptopass.asteroidradar.database.AsteroidDatabase
import com.kryptopass.asteroidradar.database.entities.DatabaseAsteroid
import com.kryptopass.asteroidradar.database.entities.asDomainModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AsteroidsDatabaseTests {

    private lateinit var asteroidDao: AsteroidDao
    private lateinit var db: AsteroidDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // in-memory database as data stored here disappears when process is killed
        db = Room.inMemoryDatabaseBuilder(context, AsteroidDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        asteroidDao = db.asteroidDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetAsteroid() {
        val asteroid = DatabaseAsteroid(
            1,
            "495615 (2015 PQ291)",
            "2023-05-21",
            17.73,
            1.6905965516,
            17.1348529314,
            0.3118856402,
            false
        )
        asteroidDao.insertAll(listOf(asteroid))
        val asteroids = asteroidDao.getAsteroids().value
        val domainAsteroids = asteroids?.asDomainModel()
        val domainAsteroid = domainAsteroids?.first()

        // TODO: getting null from in-memory-store??
    }
}