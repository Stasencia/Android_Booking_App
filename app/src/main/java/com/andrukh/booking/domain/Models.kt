package com.andrukh.booking.domain

data class HotelRoom(
    var roomId: Long = 0L,
    var name: String = "",
    var price: Double = 0.0,
    var description: String = "",
    var imageURL: String?
) {

    /**
     * Short description is used for displaying truncated descriptions in the UI
     *//*
    val shortDescription: String
        get() = description.smartTruncate(200)*/
}
