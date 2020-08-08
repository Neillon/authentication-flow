package com.neillon.authentication_flow.utils

import com.neillon.domain.entities.User

sealed class AuthenticationProcessState {
    object LoadingUser: AuthenticationProcessState()
    class LoadTraining(var user: User): AuthenticationProcessState()
    object LoadAuthenticationProcess: AuthenticationProcessState()
}