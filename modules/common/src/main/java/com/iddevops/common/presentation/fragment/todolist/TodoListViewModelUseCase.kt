package com.iddevops.common.presentation.fragment.todolist

import com.iddevops.common.domain.model.TodoModel
import com.iddevops.core.common.data.request.RequestState
import com.iddevops.core.common.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class TodoListViewModelUseCase: BaseViewModel() {
    abstract val listTodos: StateFlow<RequestState<List<TodoModel>>>
    abstract var currentTodosPage: MutableStateFlow<Int>
    abstract var canLoadMore: MutableStateFlow<Boolean>
    abstract fun getTodos()
    abstract fun loadMoreTodos()
    abstract val cacheTodos: StateFlow<RequestState<List<TodoModel>>>
    abstract fun getCacheTodos()
}