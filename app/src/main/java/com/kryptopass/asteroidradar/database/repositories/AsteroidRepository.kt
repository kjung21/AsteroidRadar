package com.kryptopass.asteroidradar.database.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.kryptopass.asteroidradar.network.NeoApi
import com.kryptopass.asteroidradar.network.parseAsteroidsJsonResult
import com.kryptopass.asteroidradar.database.AsteroidDatabase
import com.kryptopass.asteroidradar.database.entities.asDatabaseModel
import com.kryptopass.asteroidradar.database.entities.asDomainModel
import com.kryptopass.asteroidradar.domain.Asteroid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepository(private val database: AsteroidDatabase) {

    val asteroids: LiveData<List<Asteroid>?> = database.asteroidDao.getAsteroids().map {
        it?.let {
            it.asDomainModel()
        }
    }

    suspend fun refresh()
    {
        withContext(Dispatchers.IO)
        {
            val asteroids = parseAsteroidsJsonResult(JSONObject(NeoApi.retrofitService.getAsteroids()))
            database.asteroidDao.insertAll(asteroids.asDatabaseModel())
        }
    }
}

