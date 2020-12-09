package com.andrukh.booking.screens.hotelRoom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andrukh.booking.R
import com.andrukh.booking.database.HotelRoom

class HotelRoomRecyclerViewAdapter :
    RecyclerView.Adapter<HotelRoomRecyclerViewAdapter.ViewHolder>() {

    var data = listOf<HotelRoom>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.roomName.text = item.name
        holder.roomPrice.text = item.price.toString()
        holder.roomDescription.text = item.description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item_hotel_room, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomName: TextView = itemView.findViewById(R.id.room_name)
        val roomPrice: TextView = itemView.findViewById(R.id.room_price)
        val roomDescription: TextView = itemView.findViewById(R.id.room_description)
    }

}