package com.neillon.network.response

data class PagedResponse<T>(
    var data: T,
    val page: Int,
    val total: Int
)