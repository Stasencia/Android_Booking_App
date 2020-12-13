package com.andrukh.booking.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://api.unsplash.com"
private const val ACCESS_KEY = "qOKAOTtcN1cfTXDlEtmqSqLGGuWyC0sg34vTn1s9mKM"

/**
 * A retrofit service to fetch a devbyte playlist.
 */
interface ImageApiService {
    /**
     * Returns a Coroutine [List] of [ImageProperty] which can be fetched with await() if in a Coroutine scope.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("photos")
    suspend fun getProperties(): List<NetworkImageContainer>
}

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Main entry point for network access. Call like `Network.devbytes.getPlaylist()`
 */
object Network {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val imageService = retrofit.create(ImageApiService::class.java)
}
