package com.iddevops.core.common.util

import java.text.SimpleDateFormat
import java.util.*

fun timeToMillis(hour: Int = 0, minute: Int = 0, sec: Int = 0): Long {
    return ((hour * 60 * 60) + (minute * 60) + sec) * 1000L
}

// example : longMillisToFormattedTimeDate( 10000, "dd/MM/yyyy hh:mm a", "GMT+2" )
fun longMillisToFormattedTimeDate(
    unixTimeMillis: Long,
    outputPattern: String,
    timeZone: String? = null
): String {
    val formatter = SimpleDateFormat(outputPattern)
    formatter.timeZone =
        if (timeZone.isNullOrBlank()) TimeZone.getDefault() else TimeZone.getTimeZone(timeZone)
    return formatter.format(Date(unixTimeMillis))
}