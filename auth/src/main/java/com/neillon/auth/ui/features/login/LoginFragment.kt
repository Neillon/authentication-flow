package com.neillon.auth.ui.features.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.neillon.auth.R
import com.neillon.authentication_flow.ui.exhaustive
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val loginViewModel: LoginViewModel by activityViewModels()
    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        loginViewModel.loginState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is LoginState.Logged -> {
                    val action = LoginFragmentDirections.actionLoginFragmentToTrainingActivity()
                    navController.navigate(action)
                    activity?.finish()
                }
                is LoginState.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initializeViews() {
        mAuthLoginSignUpTextView.setOnClickListener {
            val actionGoToRegister = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            navController.navigate(actionGoToRegister)
        }

        mAuthLoginButton.setOnClickListener {
            val username = mAuthLoginEmailEditText.text.toString()
            val password = mAuthLoginPasswordEditText.text.toString()

            loginViewModel.login(username, password)
        }
    }
}