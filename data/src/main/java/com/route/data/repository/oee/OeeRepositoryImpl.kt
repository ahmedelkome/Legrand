package com.route.data.repository.oee

import com.route.data.contract.oee.OeePostOnlineDataSource
import com.route.domain.common.ResultWrapper
import com.route.domain.models.oee.OEE
import com.route.domain.repos.oee.OeeRepository
import com.route.domain.utils.toResultWrapper
import javax.inject.Inject

class OeeRepositoryImpl @Inject constructor(
    private val oeePostOnlineDataSource: OeePostOnlineDataSource
) : OeeRepository {
    override suspend fun postData(oee: OEE): ResultWrapper<Unit> {
       return toResultWrapper {
            oeePostOnlineDataSource.postData(oee)
        }
    }
}