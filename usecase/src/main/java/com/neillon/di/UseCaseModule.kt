package com.neillon.di

import com.neillon.usecase.user.GetSingleUserUseCase
import com.neillon.usecase.user.SaveUserUseCase
import org.koin.dsl.module

object UseCaseModule {
    val dependencies = module {

        factory {
            SaveUserUseCase(repository = get())
        }

        factory {
            GetSingleUserUseCase(repository = get())
        }

    }
}