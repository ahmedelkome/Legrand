package com.route.data.repository.oee

import com.route.data.contract.oee.OeeOnlineDataSource
import com.route.data.excel.contract.ExcelExporter
import com.route.domain.common.ResultWrapper
import com.route.domain.models.oee.OEE
import com.route.domain.repos.oee.OeeRepository
import com.route.domain.utils.toFlow
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

 class OeeRepositoryImpl @Inject constructor(
     private val oeeOnlineDataSource: OeeOnlineDataSource,
     private val excelExporter: ExcelExporter,
) : OeeRepository {
    override suspend fun postData(oee: OEE): Flow<ResultWrapper<String>> {
        return toFlow {
            oeeOnlineDataSource.postData(oee)
        }
    }

    override suspend fun getPartNumber(): Flow<ResultWrapper<List<String>>> {
        return toFlow {
            oeeOnlineDataSource.getPartNumber()
        }
    }

     override suspend fun exportDataToExcel(): Flow<ResultWrapper<File?>> {
        return toFlow {
             val data = oeeOnlineDataSource.exportDataToExcel()
             val file = excelExporter.exportDataToExcel(data)
             file
         }
     }

     override suspend fun editData(oee: OEE): Flow<ResultWrapper<String>> {
         return toFlow {
             oeeOnlineDataSource.editData(oee)
         }
     }
 }