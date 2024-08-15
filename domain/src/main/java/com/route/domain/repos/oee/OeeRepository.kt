package com.route.domain.repos.oee

import com.route.domain.common.ResultWrapper
import com.route.domain.models.oee.OEE

interface OeeRepository {

    suspend fun postData(oee: OEE) : ResultWrapper<Unit>
}