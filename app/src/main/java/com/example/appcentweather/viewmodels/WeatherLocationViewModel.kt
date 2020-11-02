package com.example.appcentweather.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appcentweather.WeatherWoeidToTimeActivity
import com.example.appcentweather.injection.scopes.AppScoped
import com.example.appcentweather.models.RepoLoctionWeather
import com.example.appcentweather.network.restapi.Repository
import com.example.appcentweather.until.AppConstants
import com.example.appcentweather.viewmodels.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@AppScoped
class WeatherLocationViewModel @Inject constructor(repository: Repository, viewState: WeatherViewState)
    :BaseViewModel<WeatherViewState>(repository, viewState){

    fun getWeather(woeid : Int){
        viewModelScope.launch {
            viewState.repoWeatherLocation = retrofitRepository.getWeatherLocation(woeid) as MutableLiveData<RepoLoctionWeather>
            Log.d("WeatherLocation","WeatherLocation")
            updateUi()
        }
    }

    fun launchWeatherActivity(woeid:Int, date:String){
        viewState.clearActivityOnIntent = true
        viewState.newActivity = WeatherWoeidToTimeActivity::class
        viewState.intentExtras.putInt(AppConstants.LOCATION_WOEID, woeid)
        viewState.intentExtras.putString(AppConstants.LOCATION_WOEID_DATE, date)
        updateUi()
    }
}