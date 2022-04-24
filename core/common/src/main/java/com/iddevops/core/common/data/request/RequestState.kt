package com.iddevops.core.common.data.request

/**
 * Request state
 * @author stefanus.ayudha@gmail.com
 * @param T Data type
 * @constructor Create empty Request state
 */
sealed class RequestState<T> {
    class Default<T> : RequestState<T>()
    class Loading<T> : RequestState<T>()
    class Success<T>(val data: T) : RequestState<T>()
    class Failed<T>(val e: Throwable?) : RequestState<T>()
}