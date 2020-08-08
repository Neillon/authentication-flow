package com.neillon.auth.ui.features.register

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.neillon.auth.R
import com.neillon.training.base.BaseAuthViewModel
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(R.layout.fragment_register) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
    }

    private fun initializeViews() {
        setupToolbar()
    }

    private fun setupToolbar() {
        mRegisterToolbar.setupWithNavController(findNavController())
        mRegisterToolbar.setBackgroundColor(Color.WHITE)
        mRegisterToolbar.title = ""
    }
}