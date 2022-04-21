package com.iddevops.core.common.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iddevops.core.common.data.request.RequestState
import com.iddevops.core.common.data.request.failed
import com.iddevops.core.common.data.request.success
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel() {

    private val supervisorJob = SupervisorJob()
    protected val handler: (onError: ((c: CoroutineContext, e: Throwable) -> Unit)?) -> CoroutineExceptionHandler =
        {
            CoroutineExceptionHandler { coroutineContext, throwable ->
                it?.invoke(coroutineContext, throwable)
                    ?: run {
                        Log.e(
                            "${coroutineContext::class.simpleName}",
                            "launchJob: ${throwable.localizedMessage}"
                        )
                    }
            }
        }

    protected fun <T> requester(
        reqData: MutableStateFlow<RequestState<T>>,
        task: suspend CoroutineScope.() -> Flow<T>?
    ) = viewModelScope.launch(
        Dispatchers.Default
                + supervisorJob
                + handler { _, e ->
            reqData.failed(e)
        })
    {
        task.invoke(this)?.onEach { coroutineContext.ensureActive() }?.collect {
            reqData.success(it)
        } ?: run {
            reqData.failed(NullPointerException())
        }
    }
}