package com.sopt.now

import android.app.Application

class MyApplication : Application() {
    companion object {
        lateinit var prefs: preferenceUtil
    }

    override fun onCreate() {
        prefs = preferenceUtil(applicationContext)
        super.onCreate()
    }
}