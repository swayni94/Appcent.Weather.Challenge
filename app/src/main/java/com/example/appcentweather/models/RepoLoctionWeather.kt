package com.example.appcentweather.models

import com.google.gson.annotations.SerializedName


data class RepoLoctionWeather(
                            @SerializedName("consolidated_weather")
                            val consolidated_weather : List<Consolidated_Weather>?,
                            @SerializedName("time")
                            val time : String?,
                            @SerializedName("sun_rise")
                            val sun_rise : String?,
                            @SerializedName("sun_set")
                            val sun_set : String?,
                            @SerializedName("timezone_name")
                            val timezone_name : String?,
                            @SerializedName("parent")
                            val parent : Parent?,
                            @SerializedName("sources")
                            val sources : List<SourceModel>?,
                            @SerializedName("title")
                            val title : String?,
                            @SerializedName("location_type")
                            val location_type : String?,
                            @SerializedName("woeid")
                            val woeid : Long?,
                            @SerializedName("latt_long")
                            val latt_long : String?,
                            @SerializedName("timezone")
                            val timezone : String?)
/*
{
    data class WeatherState(val id : Int,
                            val weather_state_name : String,
                            val weather_state_abbr : String,
                            val wind_direction_compass : String,
                            val created : String,
                            val applicable_date : String,
                            val min_temp : Double,
                            val max_temp : Double,
                            val the_temp : Double,
                            val wind_speed : Double,
                            val wind_direction : Double,
                            val air_pressure : Double,
                            val humidity : Int,
                            val visibility : Double,
                            val predictability : Int)

    data class Parent (val title : String,
                       val location_type : String,
                       val woeid : Int,
                       val latt_long : String)

    data class SourceModel(val title : String,
                           val slug : String,
                           val url : String,
                           val crawl_rate : Int)
}
 */
