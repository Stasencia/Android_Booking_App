package com.andrukh.booking.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HotelRoomDAO {

    @Insert
    suspend fun insert(room: HotelRoom)

    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param room new value to write
     */
    @Update
    suspend fun update(room: HotelRoom)

    /**
     * Selects and returns the row that matches the supplied start time, which is our key.
     *
     * @param key roomId to match
     */
    @Query("SELECT * from room_table WHERE roomId = :key")
    suspend fun get(key: Long): HotelRoom?

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
    fun getAllRooms(): LiveData<List<HotelRoom>>


}