package com.andrukh.booking.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.andrukh.booking.database.BookingDatabase
import com.andrukh.booking.database.asDomainModel
import com.andrukh.booking.domain.HotelRoom
import com.andrukh.booking.network.Network
import com.andrukh.booking.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HotelRoomRepository(private val database: BookingDatabase) {

    /**
     * A playlist of videos that can be shown on the screen.
     */
    val rooms: LiveData<List<HotelRoom>> =
        Transformations.map(database.hotelRoomDao.getAllRooms()) {
            it.asDomainModel()
        }

    /**
     * Refresh the videos stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * To actually load the videos for use, observe [videos]
     */
    suspend fun refreshPhotos() {
        withContext(Dispatchers.IO) {
            val images = Network.imageService.getProperties()[0]
            database.hotelRoomDao.insertAll(*images.asDatabaseModel())
        }
    }
}
