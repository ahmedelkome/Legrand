package com.route.domain.usecases.injection

import com.route.domain.common.ResultWrapper
import com.route.domain.models.injection.InjectionData
import com.route.domain.repos.injection.InjectionRepository
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class InjectionUseCase @Inject constructor(
    private val injectionRepository: InjectionRepository
) {
    suspend fun execute(): Flow<ResultWrapper<List<InjectionData>>> {
        return injectionRepository.getInjectionData()
    }

    suspend fun getParameterSheet(partNumber: String): Flow<ResultWrapper<File?>> {
        return injectionRepository.getParameterSheet(partNumber)
    }
}