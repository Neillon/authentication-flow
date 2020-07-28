package com.neillon.auth.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.neillon.auth.databinding.ActivityAuthBinding
import com.neillon.auth.ui.features.login.LoginFragmentDirections
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()

        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        navController.navigate(action)
    }

    private fun setupNavigation() {
        mAuthNavhostFragment?.let { navController = it.findNavController() }
//        mAuthToolbar?.let {
//            setSupportActionBar(mAuthToolbar)
//            setupActionBarWithNavController(navController)
//        }
    }

    override fun onSupportNavigateUp(): Boolean =
        navController?.navigateUp() || super.onSupportNavigateUp()
}