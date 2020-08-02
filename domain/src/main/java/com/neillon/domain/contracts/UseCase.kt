package com.neillon.domain.contracts

interface UseCase <T, Params> {
    suspend fun execute(params: Params): T
}