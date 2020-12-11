package com.andrukh.booking

import android.content.res.Resources

fun convertPriceToString(price: Double, res: Resources): String {
    return res.getString(R.string.price, price)
}
