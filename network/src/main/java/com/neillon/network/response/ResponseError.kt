package com.neillon.network.response

data class ResponseError(
    val param: String,
    val location: String,
    val message: String
)