package com.neillon.network.base

data class PagedResponse<T>(
    var data: T,
    val next: String,
    val previous: String,
    val page: Int,
    val total: Int
)