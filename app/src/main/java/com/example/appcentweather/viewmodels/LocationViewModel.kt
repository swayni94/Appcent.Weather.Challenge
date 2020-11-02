package com.example.appcentweather.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appcentweather.WeatherLocationActivity
import com.example.appcentweather.injection.scopes.AppScoped
import com.example.appcentweather.models.RepoLocation
import com.example.appcentweather.network.restapi.Repository
import com.example.appcentweather.until.AppConstants
import com.example.appcentweather.viewmodels.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@AppScoped
class LocationViewModel @Inject constructor(retrofitRepository: Repository, viewState: LocationViewState
) :BaseViewModel<LocationViewState>(retrofitRepository, viewState){

    fun getLocation(lat:Double, lon:Double)
    {
        viewModelScope.launch {
            viewState.repoLocation = retrofitRepository.getLocation(lat, lon) as MutableLiveData<List<RepoLocation>>
            updateUi()
            Log.d("Location","Location")
        }
    }

    fun launchWeatherActivity(woeid:Int){
        viewState.clearActivityOnIntent = true
        viewState.newActivity =WeatherLocationActivity::class
        viewState.intentExtras.putInt(AppConstants.LOCATION_WOEID, woeid)
        updateUi()
    }
}