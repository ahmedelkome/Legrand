package com.route.domain.repos.auth

import com.route.domain.common.ResultWrapper
import com.route.domain.models.auth.UserLogin
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun login(userLogin: UserLogin): Flow<ResultWrapper<UserLogin>>

}