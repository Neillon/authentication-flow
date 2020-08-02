package com.neillon.domain.contracts

interface Repository<T> {
    suspend fun insert(value: T): Long
    suspend fun getById(id: Long): T?
    suspend fun getFirst(): T?
}