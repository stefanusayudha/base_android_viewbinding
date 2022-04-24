package com.iddevops.core.common.data

// use to store base URL securely by android NDK
object Secured {
    init {
        System.loadLibrary("native-lib")
    }

    external fun getBaseUrl(): String
}