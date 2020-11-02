package com.example.appcentweather.viewmodels

import com.example.appcentweather.MainActivity
import com.example.appcentweather.fragment.WeatherWoeidToTimeFragment
import com.example.appcentweather.injection.providers.BaseResourceProvider
import com.example.appcentweather.injection.providers.ResourceProvider
import com.example.appcentweather.injection.scopes.ActivityScoped
import com.example.appcentweather.injection.scopes.FragmentScoped
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


@Module(includes = [WeatherWoeidToTimeModule.WeatherWoeidToTimeAbstractModule::class])
class WeatherWoeidToTimeModule {

    @ActivityScoped
    @Provides
    internal fun provideResourceProvider(context: MainActivity) : BaseResourceProvider {
        return ResourceProvider(context)
    }

    @Module
    interface WeatherWoeidToTimeAbstractModule{
        @FragmentScoped
        @ContributesAndroidInjector
        fun weatherWoeidToTimeFragment() : WeatherWoeidToTimeFragment
    }
}