package com.neillon.authentication_flow.ui

import androidx.lifecycle.*
import com.neillon.authentication_flow.utils.AuthenticationProcessState
import com.neillon.domain.entities.User
import com.neillon.usecase.user.GetSingleUserUseCase
import com.neillon.usecase.user.SaveUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class MainViewModel(
    private val saveUserUseCase: SaveUserUseCase,
    private val getSingleUserUseCase: GetSingleUserUseCase
): ViewModel() {

    private val _authenticationProcessState: MutableLiveData<AuthenticationProcessState> = liveData {
        emit(AuthenticationProcessState.LoadingUser)
        val user = getLoggedUser()
        if (user == null)
            emit(AuthenticationProcessState.LoadAuthenticationProcess)
        else
            emit(AuthenticationProcessState.LoadTraining(user))
    } as MutableLiveData<AuthenticationProcessState>
    val authenticationProcessState: LiveData<AuthenticationProcessState> = _authenticationProcessState

    private suspend fun getLoggedUser(): User? {
        val userJob = viewModelScope.async(Dispatchers.IO) {
            getSingleUserUseCase.execute(GetSingleUserUseCase.NoParams())
        }
        return userJob.await()
    }

}