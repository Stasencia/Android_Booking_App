package com.andrukh.booking.screens.bookingResult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookingResultViewModel(payerName: String, travelType: String) : ViewModel() {

    private val _payerName = MutableLiveData<String>()
    val payerName: LiveData<String>
        get() = _payerName

    private val _travelType = MutableLiveData<String>()
    val travelType: LiveData<String>
        get() = _travelType

    // Event which triggers booking cancellation
    private val _eventBookingCanceled = MutableLiveData<Boolean>()
    val eventBookingCanceled: LiveData<Boolean>
        get() = _eventBookingCanceled

    init {
        _payerName.value = payerName
        _travelType.value = travelType
        _eventBookingCanceled.value = false
    }

    fun cancelBooking() {
        _eventBookingCanceled.value = true
    }

}