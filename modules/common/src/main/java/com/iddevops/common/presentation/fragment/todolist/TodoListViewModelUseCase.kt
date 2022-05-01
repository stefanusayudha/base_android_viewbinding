package com.iddevops.common.presentation.fragment.todolist

import com.iddevops.common.domain.model.TodoModel
import com.iddevops.core.common.data.request.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface TodoListViewModelUseCase {
    val listTodos: StateFlow<RequestState<List<TodoModel>>>
    var currentTodosPage: MutableStateFlow<Int>
    var canLoadMore: MutableStateFlow<Boolean>
    fun getTodos()
    fun loadMoreTodos()
    val cacheTodos: StateFlow<RequestState<List<TodoModel>>>
    fun getCacheTodos()
}