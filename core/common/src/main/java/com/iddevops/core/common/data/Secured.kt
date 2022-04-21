package com.iddevops.core.common.data

object Secured {
    init {
        System.loadLibrary("native-lib")
    }

    external fun getBaseUrl(): String
}