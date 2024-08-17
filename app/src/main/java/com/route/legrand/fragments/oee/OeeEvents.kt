package com.route.legrand.fragments.oee

import java.io.File

sealed class OeeEvents {
    data class SuccessFullyExport(val file:File?) : OeeEvents()
}