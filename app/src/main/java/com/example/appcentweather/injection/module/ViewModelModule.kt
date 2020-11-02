package com.example.appcentweather.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appcentweather.injection.ViewModelKey
import com.example.appcentweather.injection.scopes.AppScoped
import com.example.appcentweather.viewmodels.LocationViewModel
import com.example.appcentweather.viewmodels.ViewModelFactory
import com.example.appcentweather.viewmodels.WeatherLocationViewModel
import com.example.appcentweather.viewmodels.WeatherWoeidToTimeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @AppScoped
    abstract fun bindViewModelFactory(factory : ViewModelFactory) : ViewModelProvider.Factory
    @Binds
    @IntoMap
    @ViewModelKey(LocationViewModel::class)
    abstract fun bindLocationViewModel(viewModel: LocationViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherLocationViewModel::class)
    abstract fun bindWeatherLocationViewModel(weatherLocationViewModel : WeatherLocationViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherWoeidToTimeViewModel::class)
    abstract fun bindWeatherWoeidToTimeViewModel(viewModel: WeatherWoeidToTimeViewModel) : ViewModel
}