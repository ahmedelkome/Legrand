package com.route.legrand.myapp

import android.app.ActivityManager
import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import android.os.Process


@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (isMainProcess()) {
            FirebaseApp.initializeApp(this)
        }
    }

    private fun isMainProcess(): Boolean {
        val processName = getNowProcess()
        return processName == applicationContext.packageName
    }

    private fun getNowProcess(): String? {
        val manager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        return manager.runningAppProcesses?.find { it.pid == Process.myPid() }?.processName
    }
}