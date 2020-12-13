package com.andrukh.booking.network

import com.andrukh.booking.database.DatabaseHotelRoom
import com.andrukh.booking.domain.HotelRoom
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkImageContainer(val images: List<ImageProperty>)


/**
 * Videos represent a devbyte that can be played.
 */
@JsonClass(generateAdapter = true)
data class NetworkHotelRoom(
    var roomId: Long = 0L,
    var name: String = "",
    var price: Double = 0.0,
    var description: String = "",
    var image: ImageProperty
)

@JsonClass(generateAdapter = true)
data class ImageProperty(
    val id: String,
    // used to map img_src from the JSON to imgSrcUrl in our class
    @Json(name = "urls") val imgSrcUrl: ImageURL
)

/**
 * Convert Network results to database objects
 */
fun NetworkImageContainer.asDomainModel(): List<HotelRoom> {
    return images.map {
        HotelRoom(
            imageURL = it.imgSrcUrl.small
        )
    }
}

fun NetworkImageContainer.asDatabaseModel(): Array<DatabaseHotelRoom> {
    return images.map {
        DatabaseHotelRoom(
            imageURL = it.imgSrcUrl.small
        )
    }.toTypedArray()
}
