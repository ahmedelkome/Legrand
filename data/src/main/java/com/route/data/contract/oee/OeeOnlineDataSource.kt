package com.route.data.contract.oee

import com.route.domain.models.oee.OEE

interface OeeOnlineDataSource {

    suspend fun postData(oee: OEE):String

    suspend fun getPartNumber():List<String>

    suspend fun exportDataToExcel():List<Map<String,Any>>

    suspend fun editData(oee: OEE):String
}