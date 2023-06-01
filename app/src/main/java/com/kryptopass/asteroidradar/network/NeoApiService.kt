package com.kryptopass.asteroidradar.network

import com.kryptopass.asteroidradar.app.Constants
import com.kryptopass.asteroidradar.domain.ImageOfDay
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()

interface NeoApiService {
    @GET("${Constants.NEO_ENDPOINT}?api_key=${Constants.API_KEY}")
    suspend fun getAsteroids(): String

    @GET("${Constants.POD_ENDPOINT}?api_key=${Constants.API_KEY}")
    suspend fun getImageOfDay(): ImageOfDay
}

object NeoApi {
    val retrofitService: NeoApiService by lazy { retrofit.create(NeoApiService::class.java) }
}
