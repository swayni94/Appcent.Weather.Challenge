package com.example.appcentweather.viewmodels

import com.example.appcentweather.WeatherLocationActivity
import com.example.appcentweather.fragment.WeatherLocationFragment
import com.example.appcentweather.injection.providers.BaseResourceProvider
import com.example.appcentweather.injection.providers.ResourceProvider
import com.example.appcentweather.injection.scopes.ActivityScoped
import com.example.appcentweather.injection.scopes.FragmentScoped
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


@Module(includes = [WeatherLocationModule.WeatherLocationAbstractModule::class])
class WeatherLocationModule {

    @ActivityScoped
    @Provides
    internal fun provideResourceProvider(context : WeatherLocationActivity) : BaseResourceProvider{
        return ResourceProvider(context)
    }

    @Module
    interface WeatherLocationAbstractModule{
        @FragmentScoped
        @ContributesAndroidInjector
        fun weatherLocationFragment() : WeatherLocationFragment
    }
}