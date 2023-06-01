package com.kryptopass.asteroidradar.app

import com.kryptopass.asteroidradar.BuildConfig
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Constants {
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "https://api.nasa.gov/"

    const val API_KEY = BuildConfig.NASA_API_KEY
    const val NEO_ENDPOINT = "neo/rest/v1/feed"
    const val POD_ENDPOINT = "planetary/apod"

    val CURRENT_TIME: Date = Calendar.getInstance().time
    val DATE_FORMAT = SimpleDateFormat(API_QUERY_DATE_FORMAT, Locale.getDefault())
}