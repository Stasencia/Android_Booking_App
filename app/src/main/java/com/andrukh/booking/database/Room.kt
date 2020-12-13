package com.andrukh.booking.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Database(entities = [DatabaseHotelRoom::class], version = 2, exportSchema = false)
abstract class BookingDatabase : RoomDatabase() {
    abstract val hotelRoomDao: HotelRoomDao
}

@Dao
interface HotelRoomDao {
    @Insert
    suspend fun insert(room: DatabaseHotelRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg rooms: DatabaseHotelRoom)

    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param room new value to write
     */
    @Update
    suspend fun update(room: DatabaseHotelRoom)

    /**
     * Selects and returns the row that matches the supplied start time, which is our key.
     *
     * @param key roomId to match
     */
    @Query("SELECT * from room_table WHERE roomId = :key")
    suspend fun get(key: Long): DatabaseHotelRoom?

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM room_table")
    suspend fun clear()

    /**
     * Selects and returns all rows in the table,
     *
     * sorted by start time in descending order.
     */
    @Query("SELECT * FROM room_table ORDER BY roomId")
    fun getAllRooms(): LiveData<List<DatabaseHotelRoom>>
}

private lateinit var INSTANCE: BookingDatabase

fun getDatabase(context: Context): BookingDatabase {
    synchronized(BookingDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                BookingDatabase::class.java,
                "booking_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}