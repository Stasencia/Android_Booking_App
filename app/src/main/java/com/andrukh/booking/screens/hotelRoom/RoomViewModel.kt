package com.andrukh.booking.screens.hotelRoom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.andrukh.booking.database.getDatabase
import com.andrukh.booking.network.ImageProperty
import com.andrukh.booking.repository.HotelRoomRepository
import kotlinx.coroutines.launch

class RoomViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val hotelRoomRepository = HotelRoomRepository(database)

    init {
        viewModelScope.launch {
            hotelRoomRepository.refreshPhotos()
        }
    }

    val rooms = hotelRoomRepository.rooms

    // The internal MutableLiveData String that stores the most recent response status
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the status String
    val status: LiveData<String>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the MarsProperty with
    // new values
    private val _property = MutableLiveData<ImageProperty>()

    // Internally, we use a MutableLiveData, because we will be updating the List of ImageProperty
    // with new values
    private val _properties = MutableLiveData<List<ImageProperty>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val properties: LiveData<List<ImageProperty>>
        get() = _properties

    private val _navigateToPersonalInformation = MutableLiveData<Long>()
    val navigateToPersonalInformation
        get() = _navigateToPersonalInformation

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     *//*
    init {
        getRoomImage()
    }*/

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
    /*private fun getRoomImage() {
        viewModelScope.launch {
            try {
                var listResult = ImageApi.retrofitService.getProperties()
                _status.value = "Success: ${listResult.size} Mars properties retrieved"
                if (listResult.size > 0) {
                    _properties.value = listResult
                }
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }*/
}
