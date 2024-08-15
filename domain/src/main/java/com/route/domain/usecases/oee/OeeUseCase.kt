package com.route.domain.usecases.oee

import com.route.domain.models.oee.OEE
import com.route.domain.repos.oee.OeeRepository
import javax.inject.Inject

class OeeUseCase @Inject constructor(
    private val oeeRepository: OeeRepository
) {

    suspend fun postData(oee: OEE) {
        oeeRepository.postData(oee)
    }
}