package ru.skillbranch.devintensive

import android.app.Application
import android.content.Context
import android.util.Log

class App : Application() {

    init {
        Log.d("M_App", "init APP")
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun applicationContext(): Context {
            Log.d("M_App.kt", "applicationContext")
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        Log.d("M_App.kt", "onCreate")
        super.onCreate()
    }
}