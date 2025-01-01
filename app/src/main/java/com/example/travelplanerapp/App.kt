package com.example.travelplanerapp

import android.app.Application
import android.content.Context
import com.example.travelplanerapp.di.AppComponent
import com.example.travelplanerapp.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        super.onCreate()
    }
}

val Context.appComponent: AppComponent
    get() = when(this) {
        is App -> this.appComponent
        else -> (applicationContext as App).appComponent
    }
