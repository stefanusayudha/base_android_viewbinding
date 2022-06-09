package com.iddevops.common.data.repo

import com.iddevops.common.data.repo.db.ContentDBUseCase
import com.iddevops.common.data.repo.web.self.ContentWebApi
import com.iddevops.common.data.repo.web.self.model.TodoResponse
import com.iddevops.common.domain.contract.ContentRepositoryContract
import com.iddevops.common.domain.model.TodoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ContentRepository(
    private val web: ContentWebApi?,
    private val db: ContentDBUseCase?
) : ContentRepositoryContract {

    override suspend fun getTodosCache(): Flow<List<TodoModel>> {
        return flow {
            db?.getTodos()?.let { cache ->
                emit(cache.map { it.toTodoModel() })
            }
        }
    }

    override suspend fun getTodos(enableCaching: Boolean): Flow<List<TodoModel>> {
        return flow {
            web?.getTodos()?.let { result ->

                emit(result.map { it.toTodoModel() })

                // store cache
                if (enableCaching) {
                    db?.clearTodos()
                    db?.saveTodos(*result.subList(0, 20).map { it.toTodoDBEntity() }.toTypedArray())
                }
            }
        }
    }
}