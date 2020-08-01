package com.neillon.auth.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neillon.auth.utils.AuthenticationState
import com.neillon.auth.utils.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BaseAuthViewModel : ViewModel() {

    private val _authenticationState =
        MutableLiveData<AuthenticationState>(AuthenticationState.Unauthorized)
    val authenticationState: LiveData<AuthenticationState> = _authenticationState

    init {
        _authenticationState.value =
            AuthenticationState.Logged(user = UserModel("neillon", "neillon"))
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (passwordIsValidForEmail(email, password)) {
                    _authenticationState.value =
                        AuthenticationState.Logged(UserModel(email, password))
                } else {
                    _authenticationState.value =
                        AuthenticationState.Error("Invalid username or password.")
                }
            }
        }
    }

    fun logout() {
        _authenticationState.value = AuthenticationState.UnLogged
    }

    private fun passwordIsValidForEmail(email: String, password: String): Boolean {
        return email == "admin" && password == "admin"
    }

}