package com.neillon.network.interceptors

import com.neillon.network.utils.TokenUtils
import okhttp3.Interceptor
import okhttp3.Response

sealed class CommonInterceptors() {

    class Auth(var tokenUtils: TokenUtils? = null) : CommonInterceptors(), Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var newUrl = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", tokenUtils!!.getToken()).build()

            var request = chain.request().newBuilder()
                .url(newUrl)
                .build()

            return chain.proceed(request)
        }
    }
}