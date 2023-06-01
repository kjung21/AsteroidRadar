package com.kryptopass.asteroidradar.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kryptopass.asteroidradar.database.entities.DatabaseImageOfDay

@Dao
interface ImageDao {
    @Query("select * from imageofday")
    fun getImageOfDay(): LiveData<DatabaseImageOfDay?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImageOfDay(image: DatabaseImageOfDay)
}