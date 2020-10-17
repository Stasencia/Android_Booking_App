package com.andrukh.booking

data class Room(
    val imageSource: String,
    val name: List<String>,
    val bedNumber: Int,
    val accommodations: List<String>,
    val price: Float
) {
}