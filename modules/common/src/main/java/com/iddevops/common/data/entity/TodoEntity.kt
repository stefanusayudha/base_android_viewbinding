package com.iddevops.common.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.iddevops.common.domain.model.TodoModel
import com.iddevops.core.common.util.ID

@Entity(
    tableName = "todos",
    indices = [
        Index(value = ["id"], unique = true),
    ]
)
data class TodoEntity(
    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: ID,

    @field:SerializedName("completed")
    @ColumnInfo(name = "completed")
    val completed: Boolean,

    @field:SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String,

    @field:SerializedName("userId")
    @ColumnInfo(name = "userId")
    val userId: String
) {
    fun toTodoModel(): TodoModel {
        return TodoModel(
            id = this.id,
            completed = this.completed,
            title = this.title,
            userId = this.userId
        )
    }
}
