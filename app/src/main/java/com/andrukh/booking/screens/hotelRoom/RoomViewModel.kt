package com.andrukh.booking.screens.hotelRoom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.andrukh.booking.database.HotelRoom
import com.andrukh.booking.database.HotelRoomDAO

class RoomViewModel(
    val database: HotelRoomDAO,
    application: Application
) : AndroidViewModel(application) {

    val rooms = database.getAllRooms()

    private val _navigateToPersonalInformation = MutableLiveData<Long>()
    val navigateToPersonalInformation
        get() = _navigateToPersonalInformation

    private suspend fun update(room: HotelRoom) {
        database.update(room)
    }

    private suspend fun insert(room: HotelRoom) {
        database.insert(room)
    }

    fun onHotelRoomSelectClicked(id: Long) {
        _navigateToPersonalInformation.value = id
    }

    fun onSleepDataQualityNavigated() {
        _navigateToPersonalInformation.value = null
    }
}
