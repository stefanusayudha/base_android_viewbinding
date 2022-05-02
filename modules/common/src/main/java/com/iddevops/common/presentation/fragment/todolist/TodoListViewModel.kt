package com.iddevops.common.presentation.fragment.todolist

import com.iddevops.common.domain.contract.ContentUseCase
import com.iddevops.common.domain.model.TodoModel
import com.iddevops.core.common.data.request.RequestData
import com.iddevops.core.common.data.request.RequestState
import com.iddevops.core.common.data.request.asImmutable
import com.iddevops.core.common.data.request.loading
import com.iddevops.core.common.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TodoListViewModel(
    private val contentUseCase: ContentUseCase?
) : BaseViewModel(), TodoListViewModelUseCase {

    private var _todoListRequestJob: Job? = null
    private val _listTodos = RequestData<List<TodoModel>>()
    override val listTodos: StateFlow<RequestState<List<TodoModel>>> get() = _listTodos.asImmutable()
    override val currentTodosPage = MutableStateFlow(1)
    override val canLoadMore: MutableStateFlow<Boolean> = MutableStateFlow(true)

    override fun getTodos() {
        _todoListRequestJob?.cancel()
        _listTodos.loading()
        _todoListRequestJob = requester(_listTodos) {
            // emulate delay
            delay(2000)
            contentUseCase?.getTodos()
        }

        // emulate pagination
        currentTodosPage.value += 1
        if (currentTodosPage.value == 3) canLoadMore.value = false
    }

    override fun getMoreTodos() {
        // emulate load more
        if (_listTodos.value !is RequestState.Loading && canLoadMore.value) getTodos()
    }

    private var _todoListCacheRequestJob: Job? = null
    private val _cacheTodos = RequestData<List<TodoModel>>()
    override val cacheTodos: StateFlow<RequestState<List<TodoModel>>> get() = _cacheTodos.asImmutable()
    override fun getCacheTodos() {
        _todoListRequestJob?.cancel()
        _cacheTodos.loading()
        _todoListCacheRequestJob = requester(_cacheTodos) {
            // emulate delay
            delay(3000)
            contentUseCase?.getTodosCache()
        }
    }

}