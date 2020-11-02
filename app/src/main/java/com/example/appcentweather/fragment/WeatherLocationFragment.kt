package com.example.appcentweather.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcentweather.MainActivity
import com.example.appcentweather.R
import com.example.appcentweather.adapter.WeatherLocationViewAdapter
import com.example.appcentweather.adapter.interfacehelper.RecyclerViewClickListenerToWeatherDate
import com.example.appcentweather.injection.scopes.ActivityScoped
import com.example.appcentweather.models.Consolidated_Weather
import com.example.appcentweather.models.RepoLoctionWeather
import com.example.appcentweather.until.AppConstants
import com.example.appcentweather.viewmodels.WeatherLocationViewModel
import com.example.appcentweather.viewmodels.WeatherViewState
import com.example.appcentweather.viewmodels.base.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_weather_location.*
import java.text.SimpleDateFormat
import javax.inject.Inject
//
@ActivityScoped
class WeatherLocationFragment @Inject constructor(override var viewModel : WeatherLocationViewModel)
    : BaseFragment<WeatherLocationViewModel, WeatherViewState> (viewModel),
    RecyclerViewClickListenerToWeatherDate {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherLocationViewModel::class.java)

        activity?.intent?.extras?.let {
            val woeid = it[AppConstants.LOCATION_WOEID] as Int

            viewModel.getWeather(woeid)
        }?: kotlin.run {
            MainActivity::class.start(true)
        }
    }
    @SuppressLint("SetTextI18n")
    override fun updateUi(state: WeatherViewState) {
        state.repoWeatherLocation?.observe(this, Observer {
            weather_Title.text = it.title + "/" + it.parent?.title
            weather_TheTemp.text = it.consolidated_weather?.get(0)?.the_temp.toString()+"\u2103"
            weather_humidity.text = "Humidity "+it.consolidated_weather?.get(0)?.humidity?.toInt().toString()+"%"
            weather_pressure.text = "Air Pressure "+it.consolidated_weather?.get(0)?.air_pressure?.toInt().toString()+" mbar"
            weather_stateName.text= it.consolidated_weather?.get(0)?.weather_state_name
            weather_windspeed.text = "Wind " + it.consolidated_weather?.get(0)?.wind_speed?.toInt().toString() + " mph"
            val url:String = "https://www.metaweather.com/static/img/weather/png/64/"+
                    it.consolidated_weather?.get(0)?.weather_state_abbr+".png"

            Picasso.get().load(url).into(weather_stateImage)

            setListAdapter(it)
        })
        state.newActivity?.start(state.clearActivityOnIntent, state.intentExtras)
    }

    override fun attachClickListeners() {

    }

    @SuppressLint("SimpleDateFormat")
    private fun setListAdapter(weather:RepoLoctionWeather){
        val simpleDateFormat = SimpleDateFormat("EEE")
        val adapter = WeatherLocationViewAdapter(weather,this, simpleDateFormat)
        weather_recyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        weather_recyclerView.adapter = adapter
    }

    override fun getLayoutResourceFile(): Int {
        return R.layout.fragment_weather_location
    }

    override fun onItemClickListener(view: View, weatherLocationToDate: Consolidated_Weather, woeid:Int) {
        val date:String = weatherLocationToDate.applicable_date.toString().replace("-", "/")
        viewModel.launchWeatherActivity(woeid, date)
    }
}