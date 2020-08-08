package com.neillon.training.base

import androidx.lifecycle.*
import com.neillon.domain.entities.User
import com.neillon.training.utils.AuthenticationState
import com.neillon.usecase.user.GetSingleUserUseCase
import com.neillon.usecase.user.SaveUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BaseAuthViewModel(
    private val getSingleUserUseCase: GetSingleUserUseCase
) : ViewModel() {

    private val _authenticationState: MutableLiveData<AuthenticationState> = liveData {
        emit(AuthenticationState.LoadingUser)
        val user = getLoggedUser()
        if (user == null)
            emit(AuthenticationState.UnLogged)
        else
            emit(AuthenticationState.Logged(user))
    } as MutableLiveData<AuthenticationState>
    val authenticationState: LiveData<AuthenticationState> = _authenticationState

    private suspend fun getLoggedUser(): User? {
        val userJob = viewModelScope.async(Dispatchers.IO) {
            getSingleUserUseCase.execute(GetSingleUserUseCase.NoParams())
        }
        return userJob.await()
    }

}