package com.neillon.usecase.user

import com.neillon.domain.contracts.Repository
import com.neillon.domain.contracts.UseCase
import com.neillon.domain.entities.User

class SaveUserUseCase(
    private val repository: Repository<User>
): UseCase<Long, SaveUserUseCase.Params> {

    data class Params(var user: User)

    override suspend fun execute(params: Params) = repository.insert(params.user)

}