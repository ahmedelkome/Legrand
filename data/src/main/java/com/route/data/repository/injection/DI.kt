package com.route.data.repository.injection

import com.route.domain.repos.injection.InjectionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class DI {

    @Binds
    abstract fun bindInjectionRepository(
        injectionRepositoryImpl: InjectionRepositoryImpl
    ): InjectionRepository

}