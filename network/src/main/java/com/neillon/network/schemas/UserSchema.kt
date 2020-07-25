package com.neillon.network.schemas

import com.neillon.domain.entities.User
import com.neillon.network.wrapper.SchemaWrapper

data class UserSchema(
    var name: String,
    var email: String,
    var password: String,
    var token: String
) : SchemaWrapper<UserSchema, User> {

    override fun toDomain(): User = User(this.name, this.email, this.password)

    override fun toData(): UserSchema = this
}