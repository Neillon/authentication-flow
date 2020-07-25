package com.neillon.persistence.wrapper

import com.neillon.domain.entities.User
import com.neillon.persistence.entity.UserEntity

fun User.toEntity(): UserEntity = UserEntity(
    id = null,
    name = this.name,
    email = this.email
)

fun UserEntity.toDomain(): User = User(
    name = this.name,
    email = this.email,
    password = null
)