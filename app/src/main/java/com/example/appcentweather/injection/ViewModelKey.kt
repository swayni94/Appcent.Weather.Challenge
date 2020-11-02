package com.example.appcentweather.injection

import androidx.lifecycle.ViewModel
import com.example.appcentweather.viewmodels.LocationViewModel
import com.example.appcentweather.viewmodels.WeatherLocationViewModel
import dagger.MapKey
import kotlin.reflect.KClass


@kotlin.annotation.Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
