package com.neillon.training.base

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.neillon.di.UseCaseModule
import com.neillon.domain.entities.User
import com.neillon.network.di.NetworkModule
import com.neillon.persistence.di.PersistenceModule
import com.neillon.training.di.TrainingModule
import com.neillon.training.utils.AuthenticationState
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.koinApplication

open class BaseAuthActivity : AppCompatActivity() {

    private val viewModel: BaseAuthViewModel by viewModels()
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadLoggedUser()

        provideDependencies()
        observeViewModel()
    }

    private fun loadLoggedUser() {
        val user = intent.getSerializableExtra("user") as User
        viewModel.setLoggedUser(user)
    }

    private fun provideDependencies() {
        koinApplication {
            loadKoinModules(modules)
        }
    }

    private fun observeViewModel() {
        viewModel.authenticationState.observe(this, Observer {
            when (it) {
                is AuthenticationState.Logged -> {
                    Log.i(TAG, "observeViewModel: Logged with ${it.user}")
                }
                is AuthenticationState.Error -> {
                    Log.i(TAG, "observeViewModel: Error ${it.errorMessage}")
                    // TODO: Show dialog fragment
                }
                AuthenticationState.UnLogged -> {
                    Log.i(TAG, "observeViewModel: UnLogged")
                    // TODO: Move to Auth feature
                }
                AuthenticationState.Unauthorized -> {
                    Log.i(TAG, "observeViewModel: Unauthorized")
                    // TODO: Show dialog fragment to move to auth feature
                }
                AuthenticationState.LoadingUser -> {
                    Log.i(TAG, "observeViewModel: Loading User")
                    // TODO: Show loading dialog fragment
                }
            }.exhaustive
        })

        viewModel.loggedUser.observe(this, Observer {
            Log.i(TAG, "observeViewModel: Logged with $it")
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