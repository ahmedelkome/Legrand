package com.route.data.contract.injection.online

import com.route.domain.models.injection.InjectionData

interface InjectionOnlineDataSource {

    suspend fun getInjectionData(): List<InjectionData>

}