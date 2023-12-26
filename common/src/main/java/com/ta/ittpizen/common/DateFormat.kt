package com.ta.ittpizen.common

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

