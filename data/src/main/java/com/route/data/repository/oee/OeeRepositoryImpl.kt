package com.route.data.repository.oee

import com.route.data.contract.oee.OeePostOnlineDataSource
import com.route.domain.common.ResultWrapper
import com.route.domain.models.oee.OEE
import com.route.domain.repos.oee.OeeRepository
import com.route.domain.utils.toFlow
import com.route.domain.utils.toResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OeeRepositoryImpl @Inject constructor(
    private val oeePostOnlineDataSource: OeePostOnlineDataSource
) : OeeRepository {
    override suspend fun postData(oee: OEE): Flow<ResultWrapper<String>> {
        return toFlow {
            oeePostOnlineDataSource.postData(oee)
        }
    }

    override suspend fun getPartNumber(): Flow<ResultWrapper<List<String>>> {
        return toFlow {
            oeePostOnlineDataSource.getPartNumber()
        }
    }
}