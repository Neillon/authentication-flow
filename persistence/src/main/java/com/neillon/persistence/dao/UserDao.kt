package com.neillon.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.neillon.persistence.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: Any): Long

    @Query(value = "SELECT * FROM user LIMIT 1")
    suspend fun getUser(): UserEntity

}