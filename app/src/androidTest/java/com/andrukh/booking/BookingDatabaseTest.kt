package com.andrukh.booking

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.andrukh.booking.database.BookingDatabase
import com.andrukh.booking.database.RoomDAO
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4::class)
class BookingDatabaseTest {

    private lateinit var roomDAO: RoomDAO
    private lateinit var db: BookingDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, BookingDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        roomDAO = db.roomDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetRoom() {
        val room1 = com.andrukh.booking.database.Room()
        roomDAO.insert(room1)
        room1.price = 5.0
        roomDAO.update(room1)
        val id = room1.roomId
        val room2 = roomDAO.get(id)
        assertEquals(room2?.price, room1?.price)
    }
}

