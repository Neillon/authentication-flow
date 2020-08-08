package com.neillon.training.base

import androidx.lifecycle.*
import com.neillon.domain.entities.User
import com.neillon.training.utils.AuthenticationState

class BaseAuthViewModel : ViewModel() {

    private val _authenticationState = liveData<AuthenticationState> {
        AuthenticationState.LoadingUser
    } as MutableLiveData<AuthenticationState>
    val authenticationState: LiveData<AuthenticationState> = _authenticationState
    val loggedUser = Transformations.map(authenticationState) {
        (authenticationState.value as AuthenticationState.Logged).user
    }

    fun setLoggedUser(user: User) {
        _authenticationState.value = AuthenticationState.Logged(user)
    }

}