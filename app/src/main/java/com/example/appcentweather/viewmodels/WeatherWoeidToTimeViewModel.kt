package com.example.appcentweather.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appcentweather.injection.scopes.AppScoped
import com.example.appcentweather.models.Consolidated_Weather
import com.example.appcentweather.network.restapi.Repository
import com.example.appcentweather.viewmodels.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@AppScoped
class WeatherWoeidToTimeViewModel @Inject constructor(repository: Repository, viewState: WeatherWoeidToTimeViewState)
    :BaseViewModel<WeatherWoeidToTimeViewState> (repository, viewState){

    fun getWeatherToTime(woeid:Int ,date:String){
        viewModelScope.launch {
            viewState.consolidated_weatherList = retrofitRepository.getWeatherLocationToTime(woeid, date) as MutableLiveData<List<Consolidated_Weather>>
            Log.d("WeatherWoeidToTimeViewModel","WeatherWoeidToTimeViewModel")
            updateUi()
        }
    }
}