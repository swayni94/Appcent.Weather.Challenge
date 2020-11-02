package com.example.appcentweather.injection.module

import com.example.appcentweather.injection.scopes.AppScoped
import com.example.appcentweather.network.location.WeatherLocationListener
import com.example.appcentweather.network.restapi.Repository
import dagger.Module
import dagger.Provides


//
@Module
class RepositoryModule{

    @Provides
    @AppScoped
    fun providesRepository() : Repository = Repository()

    @Provides
    @AppScoped
    fun providesWeatherLocationListener() : WeatherLocationListener = WeatherLocationListener()
}
