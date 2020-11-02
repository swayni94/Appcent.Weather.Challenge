package com.example.appcentweather

import android.os.Bundle
import com.example.appcentweather.fragment.WeatherWoeidToTimeFragment
import com.example.appcentweather.until.ActivityUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class WeatherWoeidToTimeActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var weatherWoeidtoTimeFragment:WeatherWoeidToTimeFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_woeid_to_time)

        var fragment = supportFragmentManager.findFragmentById(R.id.weather_activity_framelayout)

        if (fragment==null){
            fragment = weatherWoeidtoTimeFragment
            ActivityUtils.addFragmentToActivity(supportFragmentManager, fragment, R.id.weather_activity_framelayout)
        }

    }
}