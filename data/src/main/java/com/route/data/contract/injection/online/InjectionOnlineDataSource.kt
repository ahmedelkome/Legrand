package com.route.data.contract.injection.online

import com.route.domain.models.injection.InjectionData
import java.io.File

interface InjectionOnlineDataSource {

    suspend fun getInjectionData(): List<InjectionData>

    suspend fun getParameterSheet(partNumber:String): File?

}