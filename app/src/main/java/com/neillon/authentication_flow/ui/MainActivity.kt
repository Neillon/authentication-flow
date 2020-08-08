package com.neillon.authentication_flow.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.neillon.authentication_flow.R
import com.neillon.authentication_flow.databinding.ActivityMainBinding
import com.neillon.authentication_flow.utils.AuthenticationProcessState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        runBlocking {
            delay(2000L)
        }

        mMainNavHostFragment?.let { navController = it.findNavController() }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.authenticationProcessState.observe(this, Observer {
            when (it) {
                AuthenticationProcessState.LoadingUser -> {
                    binding.textViewState.isVisible = true
                }
                is AuthenticationProcessState.LoadTraining -> {
                    navController.navigate(R.id.authActivity)
                    finish()
                }
                AuthenticationProcessState.LoadAuthenticationProcess -> {
                    navController.navigate(R.id.trainingActivity)
                    finish()
                }
            }.exhaustive
        })
    }
}

val Any.exhaustive
    get() = this