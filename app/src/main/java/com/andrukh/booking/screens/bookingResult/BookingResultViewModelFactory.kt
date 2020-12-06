package com.andrukh.booking.screens.bookingResult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookingResultViewModelFactory(private val payerName: String, private val travelType: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookingResultViewModel::class.java)) {
            return BookingResultViewModel(payerName, travelType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}