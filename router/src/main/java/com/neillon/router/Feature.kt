package com.neillon.router

import org.koin.core.module.Module

sealed class Feature(val modules: List<Module>, val name: String, val entryPointClassName: String) {
    object Auth :
        Feature(
            modules = listOf(),
            name = "Authentication",
            entryPointClassName = "${AUTH_PACKAGE}.MainActivity"
        )

    object Training : Feature(
        modules = listOf(),
        name = "Training",
        entryPointClassName = "${TRAINING_PACKAGE}.TrainingActivity"
    )
}