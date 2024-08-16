package com.route.domain.usecases.oee

import com.route.domain.common.ResultWrapper
import com.route.domain.models.oee.OEE
import com.route.domain.repos.oee.OeeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OeeUseCase @Inject constructor(
    private val oeeRepository: OeeRepository
) {
    suspend fun postData(oee: OEE): Flow<ResultWrapper<String>> {
        return oeeRepository.postData(oee)
    }

    suspend fun getPartNumber(): Flow<ResultWrapper<List<String>>> {
        return oeeRepository.getPartNumber()
    }

    suspend fun updateData(oee: OEE): Flow<ResultWrapper<String>> {
        return oeeRepository.updateData(oee)
    }
}