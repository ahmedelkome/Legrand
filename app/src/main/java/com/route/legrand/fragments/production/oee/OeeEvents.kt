package com.route.legrand.fragments.production.oee

import java.io.File

sealed class OeeEvents {
    data class SuccessFullyExport(val file:File?) : OeeEvents()
}