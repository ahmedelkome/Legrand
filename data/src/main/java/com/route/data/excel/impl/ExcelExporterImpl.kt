package com.route.data.excel.impl

import android.content.Context
import android.os.Environment
import android.util.Log
import com.route.data.excel.contract.ExcelExporter
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class ExcelExporterImpl @Inject constructor(
    private val context: Context
): ExcelExporter {
    override suspend fun exportDataToExcel(data: List<Map<String, Any>>): File? {
        val workbook: Workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("OEE Sheet")

        if (data.isEmpty()) {
            Log.d("ExcelExporter", "No data to export")
            return null
        }

        val headerRow: Row = sheet.createRow(0)
        val headers = data[0].keys.toList()
        for ((index, header) in headers.withIndex()) {
            val cell: Cell = headerRow.createCell(index)
            cell.setCellValue(header)
        }

        for ((rowIndex, rowData) in data.withIndex()) {
            val row: Row = sheet.createRow(rowIndex + 1)
            for ((cellIndex, key) in headers.withIndex()) {
                val cell: Cell = row.createCell(cellIndex)
                cell.setCellValue(rowData[key].toString())
            }
        }

        val fileName = "OEE.xlsx"
        val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(downloadDir, fileName)
        val outputStream = FileOutputStream(file)
        workbook.write(outputStream)
        outputStream.close()
        workbook.close()

        Log.e("ExcelExporter", "Excel file created: ${file.absolutePath}")
        return file
    }
}