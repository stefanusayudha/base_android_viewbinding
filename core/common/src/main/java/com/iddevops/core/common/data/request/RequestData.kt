package com.iddevops.core.common.data.request

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Request data
 * @author stefanus.ayudha@gmail.com
 * @param T DataType
 * @return MutableStateFlow of the RequestState of the given data type
 */
fun <T> RequestData(): MutableStateFlow<RequestState<T>> {
    return MutableStateFlow(RequestState.Default())
}

/**
 * Default
 * @author stefanus.ayudha@gmail.com
 * @param T DataType
 */
fun <T> MutableStateFlow<RequestState<T>>.default() {
    this.value = RequestState.Default()
}

/**
 * Loading
 * @author stefanus.ayudha@gmail.com
 * @param T DataType
 */
fun <T> MutableStateFlow<RequestState<T>>.loading() {
    this.value = RequestState.Loading()
}

/**
 * Success
 * @author stefanus.ayudha@gmail.com
 * @param T DataType
 * @param data Value to emit
 */
fun <T> MutableStateFlow<RequestState<T>>.success(data: T) {
    this.value = RequestState.Success(data)
}

/**
 * Failed
 * @author stefanus.ayudha@gmail.com
 * @param T DataType
 * @param e Error
 */
fun <T> MutableStateFlow<RequestState<T>>.failed(e: Throwable?) {
    this.value = RequestState.Failed(e)
}

/**
 * As immutable
 * @author stefanus.ayudha@gmail.com
 * @param T DataType
 * @return immutable state of the given data type
 */
fun <T> MutableStateFlow<T>.asImmutable(): StateFlow<T> {
    return this
}