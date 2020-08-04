package com.neillon.auth.base

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.neillon.auth.R
import com.neillon.auth.di.AuthModule
import com.neillon.auth.ui.features.login.LoginFragmentDirections
import com.neillon.auth.utils.AuthenticationState
import com.neillon.di.UseCaseModule
import com.neillon.network.di.NetworkModule
import com.neillon.persistence.di.PersistenceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.koinApplication

open class BaseAuthActivity : AppCompatActivity() {

    val baseAuthViewModel: BaseAuthViewModel  by viewModel()
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        provideDependencies()
        observeViewModel()
    }

    private fun provideDependencies() {
        val modules = listOf(
            AuthModule.dependencies,
            UseCaseModule.dependencies,
            PersistenceModule.dependencies,
            NetworkModule.dependencies
        )
        startKoin {
            androidContext(applicationContext)
            loadKoinModules(modules)
        }
    }

    private fun observeViewModel() {
        baseAuthViewModel.authenticationState.observe(this, Observer {
            when (it) {
                is AuthenticationState.Logged -> {
                    Log.i(TAG, "Logged")
                    val action = LoginFragmentDirections.actionLoginFragmentToTrainingActivity()
                    navController.navigate(action)
                }
                is AuthenticationState.Error -> {
                    Log.i(TAG, "Error ${it.errorMessage}")
                    navController.navigate(R.id.loginFragment)
                    navController.popBackStack()
                }
                AuthenticationState.UnLogged -> {
                    Log.i(TAG, "Unlogged")
                    navController.navigate(R.id.loginFragment)
                    navController.popBackStack()
                }
                AuthenticationState.Unauthorized -> {
                    Log.i(TAG, "Unauthorized")
                    navController.navigate(R.id.loginFragment)
                    navController.popBackStack()
                    Toast.makeText(this, "Unauthorized", Toast.LENGTH_SHORT).show()
                }
            }.exhaustive
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        koinApplication {
            unloadKoinModules(AuthModule.dependencies)
        }
    }

    companion object {
        const val TAG = "Authentication"
    }
}

private val Any.exhaustive: Any
    get() = this