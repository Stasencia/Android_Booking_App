package com.andrukh.booking.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://api.unsplash.com"
private const val ACCESS_KEY = "qOKAOTtcN1cfTXDlEtmqSqLGGuWyC0sg34vTn1s9mKM"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object pointing to the desired URL
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getProperties] method
 */
interface ImageApiService {
    /**
     * Returns a Coroutine [List] of [ImageProperty] which can be fetched with await() if in a Coroutine scope.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("photos")
    suspend fun getProperties(): List<ImageProperty>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object ImageApi {
    val retrofitService: ImageApiService by lazy { retrofit.create(ImageApiService::class.java) }
}
