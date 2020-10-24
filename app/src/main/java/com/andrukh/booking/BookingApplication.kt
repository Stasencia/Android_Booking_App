package com.andrukh.booking

import android.app.Application
import timber.log.Timber

class BookingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}