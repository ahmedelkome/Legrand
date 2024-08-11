package com.route.data.contract.auth.online_auth

import com.route.domain.models.auth.UserLogin

interface AuthOnlineDataSource {
    suspend fun login(email:String,password:String): UserLogin
}