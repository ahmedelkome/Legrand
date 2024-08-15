package com.route.data.repository.oee

import com.route.domain.repos.oee.OeeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DI {

    @Binds
    abstract fun bindOeeRepository(
        oeeRepositoryImpl: OeeRepositoryImpl
    ):OeeRepository
}