package com.kryptopass.asteroidradar

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.kryptopass.asteroidradar.database.daos.ImageDao
import com.kryptopass.asteroidradar.database.ImageDatabase
import com.kryptopass.asteroidradar.database.entities.DatabaseImageOfDay
import com.kryptopass.asteroidradar.database.entities.asDomainModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4::class)
class ImagesDatabaseTests {

    private lateinit var imageDao: ImageDao
    private lateinit var db: ImageDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // in-memory database as data stored here disappears when process is killed
        db = Room.inMemoryDatabaseBuilder(context, ImageDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        imageDao = db.imageDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetImage() {
        val imageOfDay = DatabaseImageOfDay(
            "image",
            "Supernova Discovered in Nearby Spiral Galaxy M101",
            "https://apod.nasa.gov/apod/image/2305/M101Sn_Stocks_after_960.jpg"
        )
        imageDao.insertImageOfDay(imageOfDay)
        val picture = imageDao.getImageOfDay()
        val domainPicture = picture.value?.asDomainModel()

        // TODO: getting null from in-memory-store??
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetVideo() {
        val imageOfDay = DatabaseImageOfDay("video", "", "")
        imageDao.insertImageOfDay(imageOfDay)
        val picture = imageDao.getImageOfDay()
        val domainPicture = picture.value?.asDomainModel()

        // TODO: getting null from in-memory-store??
    }
}