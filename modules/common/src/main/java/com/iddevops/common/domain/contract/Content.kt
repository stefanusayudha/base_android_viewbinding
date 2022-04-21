package com.iddevops.common.domain.contract

import com.iddevops.common.domain.model.TodoModel
import kotlinx.coroutines.flow.Flow

interface ContentUseCase {
    suspend fun getTodos(): Flow<List<TodoModel>>
}

interface ContentRepositoryContract : ContentUseCase