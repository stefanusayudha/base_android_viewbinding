package com.iddevops.core.common.util

fun timeToMillis(hour: Int = 0, minute: Int = 0, sec: Int = 0): Long {
    return ((hour * 60 * 60) + (minute * 60) + sec) * 1000L
}