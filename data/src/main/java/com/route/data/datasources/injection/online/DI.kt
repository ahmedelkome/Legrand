package com.route.data.datasources.injection.online

import com.route.data.contract.injection.online.InjectionOnlineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DI {

    @Binds
    abstract fun bindInjectionDataSourceImpl(
        injectionOnlineDataSourceImpl: InjectionOnlineDataSourceImpl
    ): InjectionOnlineDataSource

}