package com.route.data.contract.oee

import com.route.domain.models.oee.OEE

interface OeePostOnlineDataSource {

    suspend fun postData(oee: OEE)

    suspend fun getPartNumber():List<String>
}