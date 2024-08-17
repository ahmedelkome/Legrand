package com.route.data.excel.impl

import android.content.Context
import com.route.data.excel.contract.ExcelExporter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DI {

    @Provides
    @Singleton
    fun provideExcelExporter(@ApplicationContext context: Context): ExcelExporter {
        return ExcelExporterImpl(context)
    }


}