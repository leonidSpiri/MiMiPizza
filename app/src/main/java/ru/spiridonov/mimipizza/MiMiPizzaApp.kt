package ru.spiridonov.mimipizza

import android.app.Application
import android.content.Context
import ru.spiridonov.mimipizza.di.DaggerApplicationComponent

class MiMiPizzaApp : Application() {
    val component by lazy {

        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
    }
}