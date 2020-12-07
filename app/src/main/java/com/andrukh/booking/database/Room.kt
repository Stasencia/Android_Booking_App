package com.andrukh.booking.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_table")
data class Room(
    @PrimaryKey(autoGenerate = true)
    var roomId: Long = 0L,

    @ColumnInfo(name = "roomName")
    var name: String = "",

    @ColumnInfo(name = "roomPrice")
    var price: Double = 0.0,

    @ColumnInfo(name = "roomDescription")
    var description: String = ""
)