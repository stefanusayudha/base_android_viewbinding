package com.iddevops.common.data.repo

import com.iddevops.common.data.repo.db.ContentDBUseCase
import com.iddevops.common.data.repo.web.self.ContentWebApi
import com.iddevops.common.domain.contract.ContentRepositoryContract
import com.iddevops.common.domain.model.TodoModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ContentRepository(
    private val web: ContentWebApi?,
    private val db: ContentDBUseCase?
) : ContentRepositoryContract {

    override suspend fun getTodosCache(): Flow<List<TodoModel>> {
        return flow {
            // emulate delay
            delay(5000)
            db?.getTodos()?.let { cache ->
                emit(cache)
            }
        }
    }

    override suspend fun getTodos(enableCaching: Boolean): Flow<List<TodoModel>> {
        return flow {
            web?.getTodos()?.let { result ->

                emit(result)

                // store cache
                if (enableCaching) {
                    db?.clearTodos()
                    db?.saveTodos(*result.subList(0,20).toTypedArray())
                }
            }
        }
    }
}