package com.kryptopass.asteroidradar.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kryptopass.asteroidradar.database.entities.DatabaseAsteroid
import retrofit2.http.DELETE
import java.util.ArrayList

@Dao
interface AsteroidDao {
    @Query("SELECT * FROM asteroids ORDER BY closeApproachDate ASC")
    fun getAsteroids(): LiveData<List<DatabaseAsteroid>?>

    @Query("SELECT * FROM asteroids WHERE closeApproachDate = :today")
    fun getTodayAsteroids(today: String): LiveData<List<DatabaseAsteroid>?>

    @Query("SELECT * FROM asteroids WHERE closeApproachDate IN (:sevenDaysDates) ORDER BY closeApproachDate ASC")
    fun getWeekAsteroids(sevenDaysDates: ArrayList<String>): LiveData<List<DatabaseAsteroid>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(asteroids: List<DatabaseAsteroid>)

    @Query("DELETE FROM asteroids WHERE closeApproachDate < :today")
    fun cleanYesterday(today: String): Int
}