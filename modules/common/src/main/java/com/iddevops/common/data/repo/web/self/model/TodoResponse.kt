package com.iddevops.common.data.repo.web.self.model

import com.google.gson.annotations.SerializedName
import com.iddevops.common.data.entity.TodoEntity
import com.iddevops.common.domain.model.TodoModel

data class TodoResponse(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("completed")
    val completed: Boolean? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("userId")
    val userId: String? = null
) {
    fun toTodoModel(): TodoModel {
        return TodoModel(
            id = this.id.orEmpty(),
            completed = this.completed ?: false,
            title = this.title.orEmpty(),
            userId = this.userId.orEmpty()
        )
    }

    fun toTodoDBEntity(): TodoEntity {
        return TodoEntity(
            id = this.id.orEmpty(),
            completed = this.completed ?: false,
            title = this.title.orEmpty(),
            userId = this.userId.orEmpty()
        )
    }
}
