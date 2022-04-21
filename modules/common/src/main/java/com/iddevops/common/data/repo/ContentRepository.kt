package com.iddevops.common.data.repo

import com.iddevops.common.data.repo.db.ContentDBUseCase
import com.iddevops.common.data.repo.web.self.ContentWebApi
import com.iddevops.common.domain.contract.ContentRepositoryContract
import com.iddevops.common.domain.model.TodoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ContentRepository(
    private val web: ContentWebApi?,
    private val db: ContentDBUseCase?
) : ContentRepositoryContract {
    override suspend fun getTodos(): Flow<List<TodoModel>> {
        val todos = web?.getTodos()
        return todos?.let { it ->
            flow {
                emit(it)
            }
        } ?: throw NullPointerException()
    }
}