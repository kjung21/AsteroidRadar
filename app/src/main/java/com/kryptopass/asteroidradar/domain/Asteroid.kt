package com.kryptopass.asteroidradar.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Asteroid(val id: Long, val codename: String, val closeApproachDate: String,
                    val absoluteMagnitude: Double, val estimatedDiameter: Double,
                    val relativeVelocity: Double, val distanceFromEarth: Double,
                    val isPotentiallyHazardous: Boolean) : Parcelable

// TODO: @Serializable -> build fails

