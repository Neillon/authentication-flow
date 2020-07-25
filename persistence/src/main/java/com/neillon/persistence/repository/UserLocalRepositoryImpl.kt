package com.neillon.persistence.repository

import com.neillon.domain.contracts.Repository
import com.neillon.domain.entities.User
import com.neillon.persistence.AppDatabase
import com.neillon.persistence.wrapper.toDomain
import com.neillon.persistence.wrapper.toEntity

class UserLocalRepositoryImpl(
    private val database: AppDatabase
) : Repository<User> {

    override suspend fun insert(value: User): Long =
        database.userDao().insert(value.toEntity())

    override suspend fun getById(id: Long): User {
        throw NotImplementedError("Method getById wasn't implemented yet")
    }

    override suspend fun getFirst(): User =
        database.userDao().getUser().toDomain()

}