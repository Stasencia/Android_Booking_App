package com.andrukh.booking.screens.hotelRoom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.andrukh.booking.database.HotelRoom
import com.andrukh.booking.database.HotelRoomDAO
import com.andrukh.booking.network.ImageApi
import kotlinx.coroutines.launch

class RoomViewModel(
    val database: HotelRoomDAO,
    application: Application
) : AndroidViewModel(application) {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    val rooms = database.getAllRooms()

    private val _navigateToPersonalInformation = MutableLiveData<Long>()
    val navigateToPersonalInformation
        get() = _navigateToPersonalInformation

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getRoomImage()
    }

    private suspend fun update(room: HotelRoom) {
        database.update(room)
    }

    private suspend fun insert(room: HotelRoom) {
        database.insert(room)
    }

    fun onHotelRoomSelectClicked(id: Long) {
        _navigateToPersonalInformation.value = id
    }

    fun onHotelRoomSelectNavigated() {
        _navigateToPersonalInformation.value = null
    }

    /**
     * Sets the value of the response LiveData to the Images API status or the successful number of
     * Room Images retrieved.
     */
    private fun getRoomImage() {
        viewModelScope.launch {
            try {
                var listResult = ImageApi.retrofitService.getProperties()
                _response.value = "Success: ${listResult.size} Mars properties retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}
