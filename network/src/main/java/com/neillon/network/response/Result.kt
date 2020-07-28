package com.neillon.network.response

sealed class Result {
    class Success<T>(response: Response<T>) : Result()
    class Error(status: Int, errors: List<ResponseError>) : Result()
}