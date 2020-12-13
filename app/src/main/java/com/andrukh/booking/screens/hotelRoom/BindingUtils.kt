package com.andrukh.booking.screens.hotelRoom

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.andrukh.booking.convertPriceToString
import com.andrukh.booking.domain.HotelRoom

@BindingAdapter("hotelRoomPriceString")
fun TextView.setHotelRoomPriceString(item: HotelRoom?) {
    item?.let {
        text = convertPriceToString(item.price, context.resources)
    }
}

@BindingAdapter("hotelRoomDescriptionString")
fun TextView.setHotelRoomDescriptionString(item: HotelRoom?) {
    item?.let {
        text = item.description
    }
}

@BindingAdapter("hotelRoomNameString")
fun TextView.setHotelRoomNameString(item: HotelRoom?) {
    item?.let {
        text = item.name
    }
}

