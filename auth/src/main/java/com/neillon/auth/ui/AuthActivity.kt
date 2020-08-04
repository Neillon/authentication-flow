package com.neillon.auth.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.neillon.auth.R
import com.neillon.auth.base.BaseAuthActivity
import com.neillon.auth.databinding.ActivityAuthBinding
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : BaseAuthActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        navController = mAuthNavhostFragment!!.findNavController()
    }

    override fun onSupportNavigateUp(): Boolean =
        navController?.navigateUp() || super.onSupportNavigateUp()
}