package com.route.data.excel.contract

import java.io.File

interface ExcelExporter {
    suspend fun exportDataToExcel(data:List<Map<String,Any>>):File?
}