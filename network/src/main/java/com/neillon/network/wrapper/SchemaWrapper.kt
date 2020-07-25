package com.neillon.network.wrapper

interface SchemaWrapper<T, S> {
    fun toDomain(): S
    fun toData(): T
}