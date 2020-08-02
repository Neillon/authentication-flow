package com.neillon.auth.base

import androidx.lifecycle.*
import com.neillon.auth.utils.AuthenticationState
import com.neillon.auth.utils.LoggedUser
import com.neillon.domain.entities.User
import com.neillon.usecase.user.GetSingleUserUseCase
import com.neillon.usecase.user.SaveUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BaseAuthViewModel(
    private val saveUserUseCase: SaveUserUseCase,
    private val getSingleUserUseCase: GetSingleUserUseCase
) : ViewModel() {

    private val _authenticationState: MutableLiveData<AuthenticationState> = liveData {
        val user = getLoggedUser()
        if (user == null)
            emit(AuthenticationState.UnLogged)
        else
            emit(AuthenticationState.Logged(LoggedUser(user.email, user.name, user.password!!)))
    } as MutableLiveData<AuthenticationState>
    val authenticationState: LiveData<AuthenticationState> = _authenticationState

    private suspend fun getLoggedUser(): User? {
        val userJob = viewModelScope.async(Dispatchers.IO) {
            getSingleUserUseCase.execute(GetSingleUserUseCase.NoParams())
        }
        return userJob.await()
    }

    fun login(email: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            // TODO: Fetch data from API request with email and password
            // TODO: Save token on sharedPreferences
            if (passwordIsValidForEmail(email, password)) {
                val loggedUser = User(email, "Neillon", password)
                val loggedUserId = saveLoggedUser(email, password)
                emitLoggedUser(loggedUser)
            } else {
                emitLoginError("Invalid email or password.")
            }
}

    private suspend fun emitLoginError(message: String) = withContext(Dispatchers.Main) {
        _authenticationState.value =
            AuthenticationState.Error(message)
    }

    private suspend fun emitLoggedUser(user: User) = withContext(Dispatchers.Main) {
        _authenticationState.value =
            AuthenticationState.Logged(LoggedUser(user.email, user.name, user.password!!))
    }

    private suspend fun saveLoggedUser(email: String, password: String): Long {
        val saveUserJob = viewModelScope.async(Dispatchers.IO) {
            val user = User(
                email = email,
                password = password,
                name = "logged"
            )
            saveUserUseCase.execute(
                SaveUserUseCase.Params(user)
            )
        }
        return saveUserJob.await()
    }

    fun logout() {
        _authenticationState.value = AuthenticationState.UnLogged
        removeLoggedUser()
    }

    private fun removeLoggedUser() {
        viewModelScope.launch { }
    }

    private fun passwordIsValidForEmail(email: String, password: String): Boolean {
        return email == "admin" && password == "admin"
    }

}