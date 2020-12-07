package com.andrukh.booking.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RoomDAO {

    @Insert
    suspend fun insert(night: Room)

    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param room new value to write
     */
    @Update
    suspend fun update(room: Room)

    /**
     * Selects and returns the row that matches the supplied start time, which is our key.
     *
     * @param key roomId to match
     */
    @Query("SELECT * from room_table WHERE roomId = :key")
    suspend fun get(key: Long): Room?

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
    @Query("SELECT * FROM room_table ORDER BY roomId DESC")
    fun getAllRooms(): LiveData<List<Room>>


}