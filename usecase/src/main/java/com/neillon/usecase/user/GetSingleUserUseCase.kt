package com.neillon.usecase.user

import com.neillon.domain.contracts.Repository
import com.neillon.domain.contracts.UseCase
import com.neillon.domain.entities.User

class GetSingleUserUseCase(
    private val repository: Repository<User>
): UseCase<User?, GetSingleUserUseCase.NoParams> {

    class NoParams

    override suspend fun execute(params: NoParams): User? = repository.getFirst()
}