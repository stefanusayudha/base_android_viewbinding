package com.iddevops.common.data.repo.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iddevops.common.data.entity.TodoEntity
import com.iddevops.common.data.repo.db.ContentDBUseCase
import com.iddevops.core.common.data.contract.DBService

@Dao
interface TodoDao : DBService, ContentDBUseCase {

    @Query("SELECT * FROM `todos`")
    override suspend fun getTodos(): List<TodoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun saveTodos(vararg todo: TodoEntity)

    @Query("DELETE FROM `todos`")
    override suspend fun clearTodos()

//    @Query("SELECT * FROM `todos`")
//    suspend fun read(): List<TodoEntity>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun save(vararg entity: TodoEntity)
//
//    @Update
//    suspend fun update(entity: TodoEntity)
//
//    @Query("DELETE FROM `todos` WHERE id = :id")
//    suspend fun delete(id: String)
//
//    @Query("DELETE FROM `todos`")
//    suspend fun clear()
}