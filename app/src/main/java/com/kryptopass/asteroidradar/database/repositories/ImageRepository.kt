package com.kryptopass.asteroidradar.database.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.kryptopass.asteroidradar.network.NeoApi
import com.kryptopass.asteroidradar.database.ImageDatabase
import com.kryptopass.asteroidradar.database.entities.asDatabaseModel
import com.kryptopass.asteroidradar.database.entities.asDomainModel
import com.kryptopass.asteroidradar.domain.ImageOfDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageRepository(private val database: ImageDatabase) {

    val imageOfDay: LiveData<ImageOfDay?> = database.imageDao.getImageOfDay().map {
        it?.let {
            it.asDomainModel()
        }
    }

    suspend fun refresh()
    {
        withContext(Dispatchers.IO)
        {
            val imageOfDay = NeoApi.retrofitService.getImageOfDay()
            if (imageOfDay.mediaType == "image") {
                database.imageDao.insertImageOfDay(imageOfDay.asDatabaseModel())
            }
        }
    }
}