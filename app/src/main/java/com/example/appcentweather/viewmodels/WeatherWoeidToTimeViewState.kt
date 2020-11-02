package com.example.appcentweather.viewmodels

import androidx.lifecycle.LiveData
import com.example.appcentweather.models.Consolidated_Weather
import com.example.appcentweather.viewmodels.base.ViewState


class WeatherWoeidToTimeViewState (
    var consolidated_weatherList : LiveData<List<Consolidated_Weather>>? = null,
    var errorMessage: String = "",
) : ViewState()