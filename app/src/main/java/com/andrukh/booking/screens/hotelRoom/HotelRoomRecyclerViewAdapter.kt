package com.andrukh.booking.screens.hotelRoom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andrukh.booking.databinding.ListItemHotelRoomBinding
import com.andrukh.booking.domain.HotelRoom

class HotelRoomRecyclerViewAdapter(val clickListener: HotelRoomListener) :
    ListAdapter<HotelRoom, HotelRoomRecyclerViewAdapter.ViewHolder>(HotelRoomDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(clickListener, item)
    }

    class ViewHolder private constructor(val binding: ListItemHotelRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: HotelRoomListener, item: HotelRoom) {
            binding.hotelRoom = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ListItemHotelRoomBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
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

class HotelRoomListener(val clickListener: (sleepId: Long) -> Unit) {
    fun onClick(hotelRoom: HotelRoom) = clickListener(hotelRoom.roomId)
}
