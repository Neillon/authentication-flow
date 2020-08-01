package com.neillon.auth.ui.features.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.neillon.auth.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
    }

    private fun initializeViews() {
        mAuthLoginSignUpTextView.setOnClickListener {
            val actionGoToRegister = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            navController.navigate(actionGoToRegister)
        }

        mAuthLoginButton.setOnClickListener {
            val actionGoToTraining = LoginFragmentDirections.actionLoginFragmentToTrainingActivity()
            navController.navigate(actionGoToTraining)
            activity?.let { it.finish() }
        }
    }
}