package com.iddevops.core.common.data.request

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun <T> RequestData(): MutableStateFlow<RequestState<T>> {
    return MutableStateFlow(RequestState.Default())
}

fun <T> MutableStateFlow<RequestState<T>>.default() {
    this.value = RequestState.Default()
}

fun <T> MutableStateFlow<RequestState<T>>.loading() {
    this.value = RequestState.Loading()
}

fun <T> MutableStateFlow<RequestState<T>>.success(data: T) {
    this.value = RequestState.Success(data)
}

fun <T> MutableStateFlow<RequestState<T>>.failed(e: Throwable?) {
    this.value = RequestState.Failed(e)
}

fun <T> MutableStateFlow<T>.asImmutable(): StateFlow<T> {
    return this
}