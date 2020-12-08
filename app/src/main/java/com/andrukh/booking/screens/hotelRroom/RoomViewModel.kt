package com.andrukh.booking.screens.hotelRroom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.andrukh.booking.database.HotelRoom
import com.andrukh.booking.database.HotelRoomDAO

class RoomViewModel(
    val database: HotelRoomDAO,
    application: Application
) : AndroidViewModel(application) {

    private val rooms = database.getAllRooms()

    private suspend fun update(room: HotelRoom) {
        database.update(room)
    }

    private suspend fun insert(room: HotelRoom) {
        database.insert(room)
    }


}
