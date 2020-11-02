package com.example.appcentweather.adapter.interfacehelper

import android.view.View
import com.example.appcentweather.models.Consolidated_Weather
import com.example.appcentweather.models.RepoLocation
//
interface RecyclerViewClickListener {
    fun onItemClickListener(view:View, location:RepoLocation)
}

interface RecyclerViewClickListenerToWeatherDate{
    fun onItemClickListener(view:View, weatherLocationToDate:Consolidated_Weather, woeid:Int)
}