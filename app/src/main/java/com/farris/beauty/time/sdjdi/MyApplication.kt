package com.farris.beauty.time.sdjdi

import android.app.Application
import com.farris.beauty.time.sdjdi.module.service.RetrofitManager

class MyApplication: Application() {

    companion object {
        lateinit var instance: MyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        RetrofitManager.init()
    }
}