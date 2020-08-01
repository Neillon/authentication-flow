package com.neillon.auth.utils

sealed class AuthenticationState {
    class Logged(var user: UserModel): AuthenticationState()
    class Error(var errorMessage: String): AuthenticationState()
    object UnLogged : AuthenticationState()
    object Unauthorized: AuthenticationState()
}

data class UserModel(var email: String, var password: String)