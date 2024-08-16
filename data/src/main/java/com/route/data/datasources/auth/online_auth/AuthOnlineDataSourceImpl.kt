package com.route.data.datasources.auth.online_auth

import com.google.firebase.auth.FirebaseAuth
import com.route.data.contract.auth.online_auth.AuthOnlineDataSource
import com.route.data.utils.safeData
import com.route.domain.models.auth.UserLogin
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthOnlineDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthOnlineDataSource {
    override suspend fun login(email: String, password: String): UserLogin {
        return safeData {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            result.user.let {
                UserLogin(
                    email = it!!.email ?: "",
                    password = password
                )
            }
        }
    }
}