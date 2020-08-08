package com.neillon.domain.entities

import java.io.Serializable

data class User(
    val name: String,
    val email: String,
    val password: String
): Serializable