package com.neillon.network.interceptors

import android.content.Context
import com.neillon.network.utils.TokenUtils
import okhttp3.Interceptor
import okhttp3.Response

sealed class CommonInterceptors(var context: Context) {

    class Auth(applicationContext: Context) : CommonInterceptors(applicationContext), Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var newUrl = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", TokenUtils.getToken(context)).build()

            var request = chain.request().newBuilder()
                .url(newUrl)
                .build()

            return chain.proceed(request)
        }
    }
}