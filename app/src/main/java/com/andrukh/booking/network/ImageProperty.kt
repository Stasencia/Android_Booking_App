package com.andrukh.booking.network

import com.squareup.moshi.Json

data class ImageProperty(
    val id: String,
    // used to map img_src from the JSON to imgSrcUrl in our class
    @Json(name = "urls") val imgSrcUrl: ImageURL
)