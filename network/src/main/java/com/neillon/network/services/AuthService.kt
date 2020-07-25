package com.neillon.network.services

import com.neillon.domain.entities.User
import com.neillon.network.base.Response
import com.neillon.network.schemas.UserSchema
import retrofit2.http.POST

interface AuthService {

    @POST("/register")
    suspend fun register(user: User): Response<UserSchema>

    @POST("/login")
    suspend fun login(user: User): Response<UserSchema>

    @POST("/forgot-password")
    suspend fun forgotPassword(email: String)

    @POST("/reset-password")
    suspend fun resetPassword(user: UserSchema)

}