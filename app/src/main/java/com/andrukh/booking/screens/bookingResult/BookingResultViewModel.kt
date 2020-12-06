package com.andrukh.booking.screens.bookingResult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookingResultViewModel : ViewModel() {
    private val payerName = ""
    private val isTravelTypeLeisure = true
    var isBookingCanceled = false

    private val _isNotificationRequired = MutableLiveData<Boolean>()
    val isNotificationRequired: LiveData<Boolean>
        get() = _isNotificationRequired

    private fun changeIsBookingCanceled() {
        isBookingCanceled = !isBookingCanceled
    }

    fun changeNotificationRequired(isNotificationRequired: Boolean) {
        _isNotificationRequired.value = isNotificationRequired
    }

    init {
        _isNotificationRequired.value = false
    }

}