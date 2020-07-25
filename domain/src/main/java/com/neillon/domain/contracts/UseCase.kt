package com.neillon.domain.contracts

interface UseCase<in T, out S> {
    suspend fun execute(data: T): S
}