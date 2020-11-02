package com.example.appcentweather.network.restapi

import com.example.appcentweather.models.Consolidated_Weather
import com.example.appcentweather.models.RepoLocation
import com.example.appcentweather.models.RepoLoctionWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

  @GET("api/location/search/")
  fun getLocation(@Query("lattlong") string: String): Call<List<RepoLocation>>

  @GET("api/location/{woeid}/")
  fun getWeatherToWoeid(@Path("woeid", encoded = true) woeid : String) : Call<RepoLoctionWeather>

  @GET("api/location/{woeid}/{date}/")
  fun getWeatherToTime(@Path("woeid", encoded = true) woeid: Int, @Path("date" , encoded = true) date: String) : Call<List<Consolidated_Weather>>

}