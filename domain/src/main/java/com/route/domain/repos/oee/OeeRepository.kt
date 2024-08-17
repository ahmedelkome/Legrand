package com.route.domain.repos.oee

import com.route.domain.common.ResultWrapper
import com.route.domain.models.oee.OEE
import kotlinx.coroutines.flow.Flow
import java.io.File

interface OeeRepository {

    suspend fun postData(oee: OEE): Flow<ResultWrapper<String>>

    suspend fun getPartNumber(): Flow<ResultWrapper<List<String>>>

    suspend fun exportDataToExcel(): Flow<ResultWrapper<File?>>

    suspend fun editData(oee: OEE): Flow<ResultWrapper<String>>
}