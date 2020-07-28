package com.neillon.network.response

data class Response<T>(
    var data: T?,
    val errors: List<ResponseError>?
)