package com.neillon.persistence.di

import com.neillon.domain.contracts.Repository
import com.neillon.domain.entities.User
import com.neillon.persistence.AppDatabase
import com.neillon.persistence.repository.UserLocalRepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module

object PersistenceModule {
    val dependencies = module {

        single {
            UserLocalRepositoryImpl(database = AppDatabase.getInstance(get())) as Repository<User>
        } bind UserLocalRepositoryImpl::class

    }
}