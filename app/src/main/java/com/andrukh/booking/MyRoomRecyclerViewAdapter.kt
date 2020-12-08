package com.andrukh.booking

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import com.andrukh.booking.dummy.DummyContent.DummyItem
import com.andrukh.booking.screens.hotelRroom.RoomFragmentDirections
import kotlinx.android.synthetic.main.fragment_item.view.*

class MyRoomRecyclerViewAdapter(
    private val values: List<DummyItem>
) : RecyclerView.Adapter<MyRoomRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.room_button.setOnClickListener {
                it.findNavController()
                    .navigate(RoomFragmentDirections.actionRoomFragmentToPersonalInformationFragment())
            }
        }

    }
}