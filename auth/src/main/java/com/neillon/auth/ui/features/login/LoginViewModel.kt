package com.neillon.auth.ui.features.login

import android.util.Log
import androidx.lifecycle.*
import com.neillon.domain.entities.User
import com.neillon.training.utils.AuthenticationState
import com.neillon.usecase.user.SaveUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    fun login(email: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            // TODO: Fetch data from API request with email and password
            // TODO: Save token on sharedPreferences
            if (passwordIsValidForEmail(email, password)) {
                val loggedUser = User(email, "Neillon", password)
                // saveLoggedUser(email, password)
                Log.i(TAG, loggedUser.toString())
                _loginState.value = LoginState.Logged(loggedUser)
            } else {
                _loginState.value = LoginState.Error("Invalid email or password.")
            }
        }

    private fun passwordIsValidForEmail(email: String, password: String): Boolean {
        return email == "admin" && password == "admin"
    }

//    private suspend fun saveLoggedUser(email: String, password: String): Long {
//        val saveUserJob = viewModelScope.async(Dispatchers.IO) {
//            val user = User(
//                email = email,
//                password = password,
//                name = "logged"
//            )
//            saveUserUseCase.execute(
//                SaveUserUseCase.Params(user)
//            )
//        }
//        return saveUserJob.await()
//    }
//
//    private fun removeLoggedUser() {
//        viewModelScope.launch { }
//    }

    companion object {
        const val TAG = "LoginViewModel"
    }
}