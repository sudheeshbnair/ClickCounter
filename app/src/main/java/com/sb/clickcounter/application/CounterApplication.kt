package com.sb.clickcounter.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CounterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}