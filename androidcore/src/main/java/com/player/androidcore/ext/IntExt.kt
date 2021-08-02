package com.player.androidcore.ext

import android.content.res.Resources

fun Int.px(): Int {
    return (this / Resources.getSystem().displayMetrics.density).toInt()
}

fun Int.dp(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}
