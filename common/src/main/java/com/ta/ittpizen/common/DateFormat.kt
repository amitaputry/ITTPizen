package com.ta.ittpizen.common

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun LocalDateTime.toChatDateFormat(): String {
    val localDate = toLocalDate()
    val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
    return localDate.format(formatter)
}

fun LocalDateTime.toChatTimeFormat(): String {
    val localDate = toLocalTime()
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return localDate.format(formatter)
}

fun String.toRelativeDateFormat(): String {
    val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.getDefault())
    val date = try {
        sdf.parse(this)
    } catch (e:Exception) {
        Date()
    }
    return DateUtils.getRelativeTimeSpanString(date.time).toString()
}
