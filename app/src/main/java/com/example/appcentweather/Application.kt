package com.example.appcentweather


import com.example.appcentweather.injection.component.AppComponent
import com.example.appcentweather.injection.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class Application : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component:AppComponent = DaggerAppComponent.builder().application(this).build()
        component.inject(this)
       return component
    }
}
