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
    override val id: ID,

    @field:SerializedName("completed")
    @ColumnInfo(name = "completed")
    override val completed: Boolean?,

    @field:SerializedName("title")
    @ColumnInfo(name = "title")
    override val title: String?,

    @field:SerializedName("userId")
    @ColumnInfo(name = "userId")
    override val userId: String
) : TodoModel
