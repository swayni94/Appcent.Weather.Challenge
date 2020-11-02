package com.example.appcentweather.viewmodels

import androidx.lifecycle.LiveData
import com.example.appcentweather.models.RepoLoctionWeather
import com.example.appcentweather.viewmodels.base.ViewState


class WeatherViewState(
    var repoWeatherLocation: LiveData<RepoLoctionWeather>? = null,
    var errorMessage: String = "",
) : ViewState()