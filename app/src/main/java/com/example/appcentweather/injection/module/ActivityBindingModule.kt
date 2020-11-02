package com.example.appcentweather.injection.module

import com.example.appcentweather.MainActivity
import com.example.appcentweather.WeatherLocationActivity
import com.example.appcentweather.WeatherWoeidToTimeActivity
import com.example.appcentweather.injection.scopes.ActivityScoped
import com.example.appcentweather.viewmodels.LocationModule
import com.example.appcentweather.viewmodels.WeatherLocationModule
import com.example.appcentweather.viewmodels.WeatherWoeidToTimeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
//
@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [LocationModule::class])
    internal abstract fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [WeatherLocationModule::class])
    internal abstract fun weatherLocationActivity() : WeatherLocationActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [WeatherWoeidToTimeModule::class])
    internal abstract fun weatherWoeidToTimeActivity() : WeatherWoeidToTimeActivity
}