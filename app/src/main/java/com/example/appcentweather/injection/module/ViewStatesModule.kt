package com.example.appcentweather.injection.module

import com.example.appcentweather.injection.scopes.AppScoped
import com.example.appcentweather.viewmodels.LocationViewState
import com.example.appcentweather.viewmodels.WeatherViewState
import com.example.appcentweather.viewmodels.WeatherWoeidToTimeViewState
import dagger.Module
import dagger.Provides


@Module
class ViewStatesModule {

    @Provides
    @AppScoped
    fun provideLocationViewState(): LocationViewState = LocationViewState()

    @Provides
    @AppScoped
    fun provideWeatherViewState(): WeatherViewState = WeatherViewState()

    @Provides
    @AppScoped
    fun provideWeatherWoeidToTimeViewState() : WeatherWoeidToTimeViewState = WeatherWoeidToTimeViewState()
}