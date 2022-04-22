package com.iddevops.common.data.repo.db

import com.iddevops.common.data.entity.TodoEntity

interface ContentDBUseCase {
    suspend fun getTodos(): List<TodoEntity>
    suspend fun saveTodos(vararg todo: TodoEntity)
    suspend fun clearTodos()
}