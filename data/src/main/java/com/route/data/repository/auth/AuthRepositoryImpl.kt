package com.route.data.repository.auth

import com.route.data.contract.auth.online_auth.AuthOnlineDataSource
import com.route.domain.common.ResultWrapper
import com.route.domain.models.auth.UserLogin
import com.route.domain.repos.auth.AuthRepository
import com.route.domain.utils.toFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authOnlineDataSource: AuthOnlineDataSource
): AuthRepository {
    override suspend fun login(email: String, password: String): Flow<ResultWrapper<UserLogin>> {
        return toFlow {
            authOnlineDataSource.login(email,password)
        }
    }
}