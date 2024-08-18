package com.route.data.datasources.oee.online

import com.route.data.contract.oee.OeeOnlineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DI {

    @Binds
    abstract fun bindOeePostOnlineDataSource(
        oeePostOnlineDataSourceImpl: OeeOnlineDataSourceImpl
    ): OeeOnlineDataSource
}