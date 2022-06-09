package com.iddevops.common.domain.contract

import com.iddevops.common.data.repo.web.self.model.TodoResponse
import com.iddevops.common.domain.model.TodoModel
import kotlinx.coroutines.flow.Flow

interface ContentUseCase {
    suspend fun getTodosCache(): Flow<List<TodoModel>>
    suspend fun getTodos(enableCaching: Boolean = true): Flow<List<TodoModel>>
}

interface ContentRepositoryContract : ContentUseCase