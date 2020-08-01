package com.neillon.auth.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.neillon.auth.R
import com.neillon.auth.ui.features.login.LoginFragmentDirections
import com.neillon.auth.utils.AuthenticationState
import kotlinx.android.synthetic.main.activity_auth.*

open class BaseAuthActivity: AppCompatActivity() {

    val baseAuthViewModel: BaseAuthViewModel by viewModels()
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        baseAuthViewModel.authenticationState.observe(this, Observer {
            when (it) {
                is AuthenticationState.Logged -> {
                    val action = LoginFragmentDirections.actionLoginFragmentToTrainingActivity()
                    navController.navigate(action)
                }
                is AuthenticationState.Error -> {
                    navController.navigate(R.id.loginFragment)
                    navController.popBackStack()
                    Toast.makeText(this, "Unauthorized", Toast.LENGTH_SHORT).show()
                }
                AuthenticationState.UnLogged -> {
                    navController.navigate(R.id.loginFragment)
                    navController.popBackStack()
                }
                AuthenticationState.Unauthorized -> {
                    navController.navigate(R.id.loginFragment)
                    navController.popBackStack()
                    Toast.makeText(this, "Unauthorized", Toast.LENGTH_SHORT).show()
                }
            }.exhaustive
        })
    }
}

private val Any.exhaustive: Any
    get() = this
