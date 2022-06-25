package com.sieun.supporters_android

import android.content.Context


fun Context.dpToPx(dp: Int): Int {
    val density = resources.displayMetrics.density
    return (dp * density).toInt()
}

fun Context.pxToDp(px: Int): Int {
    val density = resources.displayMetrics.density
    return (px / density).toInt()
}