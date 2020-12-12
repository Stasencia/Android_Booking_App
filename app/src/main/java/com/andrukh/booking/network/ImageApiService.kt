package com.andrukh.booking.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://api.unsplash.com"
private const val ACCESS_KEY = "qOKAOTtcN1cfTXDlEtmqSqLGGuWyC0sg34vTn1s9mKM"

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object pointing to the desired URL
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getProperties] method
 */
interface ImageApiService {
    /**
     * Returns a Retrofit callback that delivers a String
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("photos")
    fun getProperties(): Call<String>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object ImageApi {
    val retrofitService: ImageApiService by lazy { retrofit.create(ImageApiService::class.java) }
}
