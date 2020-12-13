package com.andrukh.booking

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andrukh.booking.network.ImageProperty
import com.andrukh.booking.screens.hotelRoom.HotelRoomRecyclerViewAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * When there is no Mars property data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ImageProperty>?) {
    val adapter = recyclerView.adapter as HotelRoomRecyclerViewAdapter
    //adapter.submitList(data)
}

/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}