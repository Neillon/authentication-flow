package com.neillon.auth.utils

sealed class AuthenticationState {
    class Logged(var user: LoggedUser): AuthenticationState()
    class Error(var errorMessage: String): AuthenticationState()
    object UnLogged : AuthenticationState()
    object Unauthorized: AuthenticationState()
}

data class LoggedUser(var email: String, var name: String, var password: String)