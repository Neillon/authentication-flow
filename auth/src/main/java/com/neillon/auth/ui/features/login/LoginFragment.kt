package com.neillon.auth.ui.features.login

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.neillon.auth.R
import com.neillon.auth.base.BaseAuthViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {

    val baseAuthViewModel: BaseAuthViewModel by activityViewModels()
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
            val username = mAuthLoginEmailEditText.text.toString()
            val password = mAuthLoginPasswordEditText.text.toString()

            baseAuthViewModel.login(username, password)
        }
    }
}