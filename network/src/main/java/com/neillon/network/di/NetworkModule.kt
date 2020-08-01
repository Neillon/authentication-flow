package com.neillon.network.di

import com.neillon.network.RetrofitServiceFactory
import com.neillon.network.interceptors.CommonInterceptors
import com.neillon.network.services.AuthService
import com.neillon.network.utils.TokenUtils
import org.koin.dsl.module

object NetworkModule {
    val dependencies = module {
        single {
            TokenUtils(context = get())
        }

        single {
            CommonInterceptors.Auth(get())
        }

        single {
            RetrofitServiceFactory.makeAuthenticatedService(tokenUtils = get(), service = AuthService::class.java)
        }
    }
}