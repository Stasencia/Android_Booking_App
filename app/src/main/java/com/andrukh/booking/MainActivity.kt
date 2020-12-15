package com.andrukh.booking

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.andrukh.booking.databinding.ActivityMainBinding
import timber.log.Timber

const val KEY_TIMER_SECONDS = "timer_seconds_key"
const val KEY_INFOCUS_TIMER_SECONDS = "timer_infocus_seconds_key"

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bookingTimer: BookingTimer

    @SuppressLint("BinaryOperationInTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup dessertTimer, passing in the lifecycle
        bookingTimer = BookingTimer(this.lifecycle)

        if (savedInstanceState != null) {
            bookingTimer.secondsCount = savedInstanceState.getInt(KEY_TIMER_SECONDS, 0)
            bookingTimer.visibleSecondsCount =
                savedInstanceState.getInt(KEY_INFOCUS_TIMER_SECONDS, 0)
        }

        Timber.i(
            "onCreate is called." +
                    " This method is called the first time the activity starts and is therefore only called once during the lifecycle of the activity. " +
                    "It represents when the activity is created and initialized. The activity is not yet visible and you can't interact with it. " +
                    "You must implement onCreate. "
        )
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    @SuppressLint("BinaryOperationInTimber")
    override fun onStart() {
        super.onStart()
        Timber.i(
            "onStart is called. This is triggered when the activity is about to become visible." +
                    " It can be called multiple times as the user navigates away from the activity and then back. " +
                    "Examples of the user \"navigating away\" are when they go to the home screen, or to a new activity in the app. " +
                    "At this point, the activity is not interactive."
        )
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume is called. This is triggered when the activity has focus and the user can interact with it.")
    }

    @SuppressLint("BinaryOperationInTimber")
    override fun onPause() {
        super.onPause()
        Timber.i(
            "onPause is called. The mirror method to onResume. " +
                    "This method is called as soon as the activity loses focus and the user can't interact with it." +
                    " An activity can lose focus without fully disappearing from the screen (for example, when a dialog appears that partially obscures the activity). "
        )
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop is called. This is the mirror method to onStart. It is called when you can no longer see the activity. ")
    }

    @SuppressLint("BinaryOperationInTimber")
    override fun onDestroy() {
        super.onDestroy()

        Timber.i(
            "onDestroy is called. This is the mirror method to onCreate. It is called once when the activity is fully destroyed." +
                    " This happens when you navigate back out of the activity (as in press the back button), or manually call finish(). " +
                    "It is your last chance to clean up resources associated with the activity.  "
        )

        val result = bookingTimer.visibleSecondsCount.toDouble() / bookingTimer.secondsCount * 100
        Timber.i(
            "The app has been working for ${bookingTimer.secondsCount} seconds. It has been in focus for ${bookingTimer.visibleSecondsCount} seconds. " +
                    "Thus, it has been in focus for %,.2f%% of time", result
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_TIMER_SECONDS, bookingTimer.secondsCount)
        outState.putInt(KEY_INFOCUS_TIMER_SECONDS, bookingTimer.visibleSecondsCount)
        Timber.i("onSaveInstanceState Called")
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}