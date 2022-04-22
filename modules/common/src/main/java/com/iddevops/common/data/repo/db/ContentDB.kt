package com.iddevops.common.data.repo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iddevops.common.data.entity.TodoEntity
import com.iddevops.common.data.repo.db.dao.TodoDao

@Database(entities = [TodoEntity::class], version = 1, exportSchema = false)
abstract class ContentDB : RoomDatabase(), ContentDBUseCase {
    companion object {
        const val DB_NAME = "ContentDB"
    }

    abstract fun todos(): TodoDao

    override suspend fun getTodos(): List<TodoEntity> {
        return todos().getTodos()
    }

    override suspend fun saveTodos(vararg todo: TodoEntity) {
        return todos().saveTodos(*todo)
    }

    override suspend fun clearTodos() {
        return todos().clearTodos()
    }
}