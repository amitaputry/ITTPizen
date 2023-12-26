package com.ta.ittpizen.common

import java.text.NumberFormat
import java.util.Locale

fun Double.toRupiah(): String {
    val localeID = Locale.forLanguageTag("in-ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    val currencyFormat = numberFormat.format(this).dropLast(3)
    return if (this == 0.0) "Rp.--" else currencyFormat
}
