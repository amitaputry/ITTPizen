package com.ta.ittpizen.common

import android.net.Uri

fun String.encode(): String {
    return Uri.encode(this)
}

fun String.decode(): String {
    return Uri.decode(this)
}
