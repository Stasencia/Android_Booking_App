package com.andrukh.booking.screens.hotelRoom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andrukh.booking.R
import com.andrukh.booking.database.HotelRoom

class HotelRoomRecyclerViewAdapter :
    ListAdapter<HotelRoom, HotelRoomRecyclerViewAdapter.ViewHolder>(HotelRoomDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val roomName: TextView = itemView.findViewById(R.id.room_name)
        private val roomPrice: TextView = itemView.findViewById(R.id.room_price)
        private val roomDescription: TextView = itemView.findViewById(R.id.room_description)

        fun bind(item: HotelRoom) {
            roomName.text = item.name
            roomPrice.text = item.price.toString()
            roomDescription.text = item.description
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.list_item_hotel_room, parent, false)

                return ViewHolder(view)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minumum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class HotelRoomDiffCallback : DiffUtil.ItemCallback<HotelRoom>() {
    override fun areItemsTheSame(oldItem: HotelRoom, newItem: HotelRoom): Boolean {
        return oldItem.roomId == newItem.roomId
    }

    override fun areContentsTheSame(oldItem: HotelRoom, newItem: HotelRoom): Boolean {
        return oldItem == newItem
    }
}
