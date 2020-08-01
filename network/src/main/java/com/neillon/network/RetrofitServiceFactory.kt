package com.neillon.network

import com.neillon.network.interceptors.CommonInterceptors
import com.neillon.network.utils.TokenUtils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceFactory {

    private val baseClient: OkHttpClient = OkHttpClient()

    fun <T> makeAuthenticatedService(tokenUtils: TokenUtils, service: Class<T>): T {
        val builder = baseClient.newBuilder()
        builder.addInterceptor(CommonInterceptors.Auth(tokenUtils = tokenUtils))

        val retrofit = Retrofit.Builder()
            .client(builder.build())
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(service)
    }

    fun <T> makeService(service: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(service)
    }
}