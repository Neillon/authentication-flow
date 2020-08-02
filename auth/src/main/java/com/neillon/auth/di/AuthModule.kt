package com.neillon.auth.di

import com.neillon.auth.base.BaseAuthViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AuthModule {
    val dependencies = module {
        viewModel {
            BaseAuthViewModel(saveUserUseCase = get(), getSingleUserUseCase = get())
        }
    }
}