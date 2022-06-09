package com.iddevops.common.data.repo.web.self

import com.iddevops.common.data.repo.web.self.model.TodoResponse
import com.iddevops.core.common.data.contract.WebService
import retrofit2.http.GET

interface ContentWebApi: WebService {
    @GET("todos")
    suspend fun getTodos(): List<TodoResponse>
}