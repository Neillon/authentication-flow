package com.neillon.auth.ui.features.login

import com.neillon.domain.entities.User

sealed class LoginState {
    class Logged(var user: User): LoginState()
    class Error(var message: String): LoginState()
}