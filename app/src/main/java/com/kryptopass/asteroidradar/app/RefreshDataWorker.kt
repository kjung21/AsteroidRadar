package com.kryptopass.asteroidradar.app

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.kryptopass.asteroidradar.app.Constants.DATE_FORMAT
import com.kryptopass.asteroidradar.database.AsteroidDatabase
import com.kryptopass.asteroidradar.database.ImageDatabase
import com.kryptopass.asteroidradar.database.repositories.AsteroidRepository
import com.kryptopass.asteroidradar.database.repositories.ImageRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    /**
     * A coroutine-friendly method to do your work.
     */
    override suspend fun doWork(): Result {
        val asteroidDatabase = AsteroidDatabase.getAsteroidDatabaseInstance(applicationContext)
        val asteroidRepository = AsteroidRepository(asteroidDatabase)
        val imageDatabase = ImageDatabase.getImageDatabaseInstance(applicationContext)
        val imageRepository = ImageRepository(imageDatabase)
        val today = DATE_FORMAT.format(Constants.CURRENT_TIME)

        return try {
            asteroidDatabase.asteroidDao.cleanYesterday(today)
            asteroidRepository.refresh()
            imageRepository.refresh()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}