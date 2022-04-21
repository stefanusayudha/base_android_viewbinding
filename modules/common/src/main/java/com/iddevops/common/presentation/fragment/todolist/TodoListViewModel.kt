package com.iddevops.common.presentation.fragment.todolist

import com.iddevops.common.domain.contract.ContentUseCase
import com.iddevops.common.domain.model.TodoModel
import com.iddevops.core.common.data.request.RequestData
import com.iddevops.core.common.data.request.RequestState
import com.iddevops.core.common.data.request.asImmutable
import com.iddevops.core.common.data.request.loading
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow

class TodoListViewModel(
    private val contentUseCase: ContentUseCase?
) : TodoListViewModelUseCase() {

    private var _todoListRequestJob: Job? = null
    private val _listTodos = RequestData<List<TodoModel>>()
    override val listTodos: StateFlow<RequestState<List<TodoModel>>> get() = _listTodos.asImmutable()

    override fun getTodos() {
        _todoListRequestJob?.cancel()
        _listTodos.loading()
        _todoListRequestJob = requester(_listTodos) {
            contentUseCase?.getTodos()
        }
    }
}