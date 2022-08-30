package com.ccarvalho.dao

import org.jetbrains.exposed.sql.ResultRow

interface DaoFacade<T> {
    fun resultRowToModel(resultRow: ResultRow): T
    suspend fun findAll(): List<T>
    suspend fun findOne(id: Long): T?
    suspend fun save(model: T): T?
    suspend fun update(id: Long, model: T): Boolean
    suspend fun delete(id: Long): Boolean
}