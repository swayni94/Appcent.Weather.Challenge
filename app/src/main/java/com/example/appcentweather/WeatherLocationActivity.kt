package com.example.appcentweather

import android.os.Bundle
import com.example.appcentweather.fragment.WeatherLocationFragment
import com.example.appcentweather.until.ActivityUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class WeatherLocationActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var weatherLocationFragment : WeatherLocationFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_location)

        var fragment = supportFragmentManager.findFragmentById(R.id.weatherFrame)

        if (fragment == null)
        {
            fragment = weatherLocationFragment
            ActivityUtils.addFragmentToActivity(supportFragmentManager, fragment , R.id.weatherFrame)
        }
    }
}