package com.example.painjournal

import android.app.Application

class TestApplication : Application() {
    var imageId: Int = 0
    override fun onCreate() {
        super.onCreate()
        // Application instance
        instance = this
    }
    companion object {
        lateinit var instance: TestApplication
    }
}