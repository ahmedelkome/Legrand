package com.route.domain.usecases.auth

import com.route.domain.common.ResultWrapper
import com.route.domain.models.auth.UserLogin
import com.route.domain.repos.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun execute(userLogin: UserLogin):Flow<ResultWrapper<UserLogin>>{
        return authRepository.login(email = userLogin.email, password = userLogin.password)

    }
}