package com.route.data.utils

suspend fun <T> safeData(dataCall: suspend () -> T): T {
    try {
        val response = dataCall.invoke()
        return response
    } catch (e: Throwable) {
        throw e
    }
}