package com.neillon.authentication_flow.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.neillon.authentication_flow.R
import com.neillon.authentication_flow.databinding.ActivityMainBinding
import com.neillon.authentication_flow.utils.AuthenticationProcessState
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.mMainNavHostFragment) as NavHostFragment?
    }
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment?.let { navController = it.findNavController() }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.authenticationProcessState.observe(this, Observer {
            when (it) {
                AuthenticationProcessState.LoadingUser -> {
                    binding.progressLoadingUser.isVisible = true
                    binding.textViewState.isVisible = true
                }
                is AuthenticationProcessState.LoadTraining -> {
                    val data = bundleOf(
                        "user" to it.user
                    )
                    navController.navigate(R.id.trainingActivity, data)
                    finish()
                }
                AuthenticationProcessState.LoadAuthenticationProcess -> {
                    navController.navigate(R.id.authActivity)
                    finish()
                }
            }.exhaustive
        })
    }
}

val Any.exhaustive
    get() = this