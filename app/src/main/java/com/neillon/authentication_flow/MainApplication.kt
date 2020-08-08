package com.neillon.authentication_flow

import android.app.Application
import com.neillon.authentication_flow.di.MainModule
import com.neillon.di.UseCaseModule
import com.neillon.network.di.NetworkModule
import com.neillon.persistence.di.PersistenceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    PersistenceModule.dependencies,
                    NetworkModule.dependencies,
                    UseCaseModule.dependencies,
                    MainModule.dependencies
                )
            )
        }

    }

}