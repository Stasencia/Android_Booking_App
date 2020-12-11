package com.andrukh.booking.screens.personalInformation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private val BOOKED_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val NO_BUZZ_PATTERN = longArrayOf(0)

class PersonalInformationViewModel : ViewModel() {

    private val _payerName = MutableLiveData<String>()
    val payerName: LiveData<String>
        get() = _payerName

    private val _travelType = MutableLiveData<String>()
    val travelType: LiveData<String>
        get() = _travelType

    fun setPersonalInfo(firstName: String, lastName: String, isLeisure: Boolean) {
        _payerName.value = "$firstName $lastName"
        _travelType.value = if (isLeisure) "Leisure" else "Business"
        _eventBuzz.value = BuzzType.BOOKED
    }

    // These are the three different types of buzzing in the game. Buzz pattern is the number of
    // milliseconds each interval of buzzing and non-buzzing takes.
    enum class BuzzType(val pattern: LongArray) {
        BOOKED(BOOKED_BUZZ_PATTERN),
        NO_BUZZ(NO_BUZZ_PATTERN)
    }

    // Event that triggers the phone to buzz using different patterns, determined by BuzzType
    private val _eventBuzz = MutableLiveData<BuzzType>()
    val eventBuzz: LiveData<BuzzType>
        get() = _eventBuzz

    fun onBuzzComplete() {
        _eventBuzz.value = BuzzType.NO_BUZZ
    }
}