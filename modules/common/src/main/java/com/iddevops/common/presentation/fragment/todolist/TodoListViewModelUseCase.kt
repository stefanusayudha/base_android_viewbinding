package com.iddevops.common.presentation.fragment.todolist

import com.iddevops.common.domain.model.TodoModel
import com.iddevops.core.common.data.request.RequestState
import com.iddevops.core.common.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class TodoListViewModelUseCase: BaseViewModel() {
    abstract val listTodos: StateFlow<RequestState<List<TodoModel>>>
    abstract fun getTodos()
}