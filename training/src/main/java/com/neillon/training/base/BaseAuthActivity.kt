package com.neillon.training.base

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.neillon.di.UseCaseModule
import com.neillon.network.di.NetworkModule
import com.neillon.persistence.di.PersistenceModule
import com.neillon.training.R
import com.neillon.training.di.TrainingModule
import com.neillon.training.utils.AuthenticationState
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.koinApplication

open class BaseAuthActivity : AppCompatActivity() {

    private val baseAuthViewModel: BaseAuthViewModel by viewModel()
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        provideDependencies()
        observeViewModel()
    }

    private fun provideDependencies() {
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
                }
                is AuthenticationState.Error -> {
                    Log.i(TAG, "Error ${it.errorMessage}")
                    // TODO: Show dialog fragment
                }
                AuthenticationState.UnLogged -> {
                    Log.i(TAG, "UnLogged")
//                    navController.navigate(R.id.authActivity)
//                    finish()
                }
                AuthenticationState.Unauthorized -> {
                    Log.i(TAG, "Unauthorized")
                    Toast.makeText(this, "Unauthorized", Toast.LENGTH_SHORT).show()
                    // TODO: Show dialog fragment to redirect
//                    navController.navigate(R.id.authActivity)
//                    finish()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        startKoin {
            androidContext(applicationContext)
            unloadKoinModules(modules)
        }
    }

    companion object {
        const val TAG = "Authentication"

        val modules = listOf(
            TrainingModule.dependencies,
            UseCaseModule.dependencies,
            PersistenceModule.dependencies,
            NetworkModule.dependencies
        )
    }
}

private val Any.exhaustive: Any
    get() = this