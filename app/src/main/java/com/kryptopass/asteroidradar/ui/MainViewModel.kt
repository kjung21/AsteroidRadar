package com.kryptopass.asteroidradar.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.kryptopass.asteroidradar.app.Constants
import com.kryptopass.asteroidradar.database.AsteroidDatabase
import com.kryptopass.asteroidradar.database.ImageDatabase
import com.kryptopass.asteroidradar.database.entities.asDomainModel
import com.kryptopass.asteroidradar.database.repositories.AsteroidRepository
import com.kryptopass.asteroidradar.database.repositories.ImageRepository
import com.kryptopass.asteroidradar.domain.Asteroid
import com.kryptopass.asteroidradar.network.getNextSevenDaysFormattedDates
import kotlinx.coroutines.launch

enum class NeoApiFilter(val value: String) { WEEK(value = "week"), DAY(value = "day"), SAVED(value = "saved") }
enum class NeoApiStatus { LOADING, ERROR, DONE }

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var today: String
    private val asteroidDatabase = AsteroidDatabase.getAsteroidDatabaseInstance(application)
    private val asteroidRepository = AsteroidRepository(asteroidDatabase)
    private val imageDatabase = ImageDatabase.getImageDatabaseInstance(application)
    private val imageRepository = ImageRepository(imageDatabase)

    private val _filter = MutableLiveData("saved")
    val filter: LiveData<String>
        get() = _filter

    private val _status = MutableLiveData<NeoApiStatus>()
    val status: LiveData<NeoApiStatus>
        get() = _status

    private val _navigateToSelectedAsteroid = MutableLiveData<Asteroid?>()
    val navigateToSelectedAsteroid: LiveData<Asteroid?>
        get() = _navigateToSelectedAsteroid

    init {
        viewModelScope.launch {
            try {
                imageRepository.refresh()
                asteroidRepository.refresh()
            } catch (e: Exception) {
                Log.i("refresh", "No Internet Connection.")
            }
        }
    }

    var asteroids = asteroidRepository.asteroids
    var imageOfDay = imageRepository.imageOfDay

    fun displayAsteroidDetails(asteroid: Asteroid) {
        _navigateToSelectedAsteroid.value = asteroid
    }

    fun displayAsteroidDetailsComplete() {
        _navigateToSelectedAsteroid.value = null
    }

    fun setFilter(filter: NeoApiFilter) {
        _filter.value = filter.value
    }

    fun updateFilter() {
        asteroids = _filter.switchMap { type ->
            when (type) {
                NeoApiFilter.DAY.value -> {
                    today = Constants.DATE_FORMAT.format(Constants.CURRENT_TIME)
                    asteroidDatabase.asteroidDao.getTodayAsteroids(today).map {
                        it?.let {
                            it.asDomainModel()
                        }
                    }
                }

                NeoApiFilter.WEEK.value -> {
                    asteroidDatabase.asteroidDao.getWeekAsteroids(getNextSevenDaysFormattedDates())
                        .map {
                            it?.let {
                                it.asDomainModel()
                            }
                        }
                }

                else -> {
                    asteroidDatabase.asteroidDao.getAsteroids().map {
                        it?.let {
                            it.asDomainModel()
                        }
                    }
                }
            }
        }
    }
}