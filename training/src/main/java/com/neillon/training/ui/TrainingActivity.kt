package com.neillon.training.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.neillon.di.UseCaseModule
import com.neillon.network.di.NetworkModule
import com.neillon.persistence.di.PersistenceModule
import com.neillon.training.base.BaseAuthActivity
import com.neillon.training.databinding.ActivityTrainingBinding
import com.neillon.training.di.TrainingModule
import kotlinx.android.synthetic.main.activity_training.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

class TrainingActivity : BaseAuthActivity() {

    private lateinit var binding: ActivityTrainingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        mTrainingNavHostFragment?.let { navController = it.findNavController() }
    }
}