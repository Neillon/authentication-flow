package com.neillon.training.utils

import com.neillon.domain.entities.User

sealed class AuthenticationState {
    class Logged(var user: User?): AuthenticationState()
    class Error(var errorMessage: String): AuthenticationState()
    object UnLogged : AuthenticationState()
    object Unauthorized: AuthenticationState()
    object LoadingUser: AuthenticationState()
}