package com.route.domain.repos.oee

import com.route.domain.common.ResultWrapper
import com.route.domain.models.oee.OEE
import kotlinx.coroutines.flow.Flow

interface OeeRepository {

    suspend fun postData(oee: OEE): Flow<ResultWrapper<String>>

    suspend fun getPartNumber(): Flow<ResultWrapper<List<String>>>
}