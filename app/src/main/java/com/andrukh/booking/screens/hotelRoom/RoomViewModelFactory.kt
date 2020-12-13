package com.andrukh.booking.screens.hotelRoom

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andrukh.booking.database.HotelRoomDao

class RoomViewModelFactory(
    private val dataSource: HotelRoomDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomViewModel::class.java)) {
            return RoomViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}