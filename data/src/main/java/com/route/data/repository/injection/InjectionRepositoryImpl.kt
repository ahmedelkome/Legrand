package com.route.data.repository.injection

import com.route.data.contract.injection.online.InjectionOnlineDataSource
import com.route.domain.common.ResultWrapper
import com.route.domain.models.injection.InjectionData
import com.route.domain.repos.injection.InjectionRepository
import com.route.domain.utils.toFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InjectionRepositoryImpl @Inject constructor(
    private val injectionOnlineDataSource: InjectionOnlineDataSource
):InjectionRepository {
    override suspend fun getInjectionData(): Flow<ResultWrapper<List<InjectionData>>> {
        return toFlow {
            injectionOnlineDataSource.getInjectionData()
        }
    }
}