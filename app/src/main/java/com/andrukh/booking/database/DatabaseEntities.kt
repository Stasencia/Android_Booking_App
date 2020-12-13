package com.andrukh.booking.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andrukh.booking.domain.HotelRoom

@Entity(tableName = "room_table")
data class DatabaseHotelRoom constructor(
    @PrimaryKey
    var roomId: Long = 0L,
    var name: String = "",
    var price: Double = 0.0,
    var description: String = "",
    var imageURL: String?
)

fun List<DatabaseHotelRoom>.asDomainModel(): List<HotelRoom> {
    return map {
        HotelRoom(
            roomId = it.roomId,
            name = it.name,
            price = it.price,
            description = it.description,
            imageURL = it.imageURL
        )
    }
}
