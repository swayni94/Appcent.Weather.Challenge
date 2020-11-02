package com.example.appcentweather.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcentweather.R
import com.example.appcentweather.WeatherLocationActivity
import com.example.appcentweather.adapter.WeatherWoeidToTimeAdapter
import com.example.appcentweather.injection.scopes.ActivityScoped
import com.example.appcentweather.models.Consolidated_Weather
import com.example.appcentweather.until.AppConstants
import com.example.appcentweather.viewmodels.WeatherWoeidToTimeViewModel
import com.example.appcentweather.viewmodels.WeatherWoeidToTimeViewState
import com.example.appcentweather.viewmodels.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_weather_woeid_to_time.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

//
@ActivityScoped
class WeatherWoeidToTimeFragment @Inject constructor(override var viewModel:WeatherWoeidToTimeViewModel)
    : BaseFragment<WeatherWoeidToTimeViewModel, WeatherWoeidToTimeViewState>(viewModel) {


    var dateString:String?=null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherWoeidToTimeViewModel::class.java)

        activity?.intent?.extras?.let {
            val woeid = it[AppConstants.LOCATION_WOEID] as Int
            val date = it[AppConstants.LOCATION_WOEID_DATE] as String
            dateString = date
            viewModel.getWeatherToTime(woeid, date)
        }?:kotlin.run {
            WeatherLocationActivity::class.start(true)
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
        "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
    )
    @SuppressLint("SimpleDateFormat", "WeekBasedYear", "SetTextI18n", "DefaultLocale")
    override fun updateUi(state: WeatherWoeidToTimeViewState) {
        state.consolidated_weatherList?.observe(this, Observer {

            val format = SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH)
            val date= dateString?.replace("/", "-")
            val dateFormat = format.parse(date)
            val simpleDateFormat = SimpleDateFormat("EEE")
            weather_time_date.text = simpleDateFormat.format(dateFormat.time).toUpperCase() + " (" + dateString + ")"

            setListAdapter(it)
        })
    }

    private fun setListAdapter(datas : List<Consolidated_Weather>){
        val adapter = WeatherWoeidToTimeAdapter(datas)
        weather_time_recyclerView.layoutManager = LinearLayoutManager(activity)
        weather_time_recyclerView.adapter = adapter
    }
    override fun attachClickListeners() {

    }

    override fun getLayoutResourceFile(): Int {
        return R.layout.fragment_weather_woeid_to_time
    }


}