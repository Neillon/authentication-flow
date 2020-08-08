package com.neillon.authentication_flow.di

import com.neillon.authentication_flow.ui.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainModule {
    val dependencies = module {
        viewModel {
            MainViewModel(saveUserUseCase = get(), getSingleUserUseCase = get())
        }
    }
}