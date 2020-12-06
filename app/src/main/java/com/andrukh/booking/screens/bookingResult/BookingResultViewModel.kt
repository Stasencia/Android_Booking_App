package com.andrukh.booking.screens.bookingResult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookingResultViewModel : ViewModel() {
    private val payerName = ""
    private val isTravelTypeLeisure = true

    // Event which triggers booking cancellation
    private val _eventBookingCanceled = MutableLiveData<Boolean>()
    val eventBookingCanceled: LiveData<Boolean>
        get() = _eventBookingCanceled

    init {
        _eventBookingCanceled.value = false
    }

    fun cancelBooking() {
        _eventBookingCanceled.value = true
    }

}