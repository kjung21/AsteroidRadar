package com.kryptopass.asteroidradar.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kryptopass.asteroidradar.domain.ImageOfDay

@Entity(tableName = "imageofday")
data class DatabaseImageOfDay constructor(
    @PrimaryKey
    val mediaType: String,
    val title: String,
    val url: String)

/**
 * Convert Database objects to domain objects
 */
fun DatabaseImageOfDay.asDomainModel(): ImageOfDay {
    return ImageOfDay (
        mediaType = mediaType,
        title = title,
        url = url)
}

/**
 * Convert domain objects to Database objects
 */
fun ImageOfDay.asDatabaseModel(): DatabaseImageOfDay
{
    return DatabaseImageOfDay(
        url = url,
        mediaType = mediaType,
        title = title
    )
}
