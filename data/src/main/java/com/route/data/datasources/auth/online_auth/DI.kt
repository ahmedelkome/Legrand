package com.route.data.datasources.auth.online_auth

import com.route.data.contract.auth.online_auth.AuthOnlineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DI {

    @Binds
    abstract fun bindAuthOnlineDataSource(
        authOnlineDataSourceImpl: AuthOnlineDataSourceImpl
    ): AuthOnlineDataSource
}