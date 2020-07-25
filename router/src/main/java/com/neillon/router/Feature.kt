package com.neillon.router

import com.neillon.auth.di.AuthModule
import com.neillon.training.di.TrainingModule
import org.koin.core.module.Module

sealed class Feature(val modules: List<Module>, val name: String, val entryPointClassName: String) {
    object Auth :
        Feature(
            modules = AuthModule.modules,
            name = "Authentication",
            entryPointClassName = "${AUTH_PACKAGE}.MainActivity"
        )

    object Training : Feature(
        modules = TrainingModule.modules,
        name = "Training",
        entryPointClassName = "${TRAINING_PACKAGE}.TrainingActivity"
    )
}