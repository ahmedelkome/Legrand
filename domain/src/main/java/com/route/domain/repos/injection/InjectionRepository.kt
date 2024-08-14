package com.route.domain.repos.injection

import com.route.domain.common.ResultWrapper
import com.route.domain.models.injection.InjectionData
import kotlinx.coroutines.flow.Flow

interface InjectionRepository {

    suspend fun getInjectionData(): Flow<ResultWrapper<List<InjectionData>>>
}