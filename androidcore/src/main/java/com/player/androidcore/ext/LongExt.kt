package com.player.androidcore.ext

import android.content.res.Resources

fun Long.px(): Int {
    return (this / Resources.getSystem().displayMetrics.density).toInt()
}

fun Long.dp(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}
