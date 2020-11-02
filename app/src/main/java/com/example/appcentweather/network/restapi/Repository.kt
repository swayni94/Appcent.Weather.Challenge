package com.example.appcentweather.network.restapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appcentweather.injection.scopes.AppScoped
import com.example.appcentweather.models.Consolidated_Weather
import com.example.appcentweather.models.RepoLocation
import com.example.appcentweather.models.RepoLoctionWeather
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@AppScoped
class Repository {

    private val baseUrl:String = "https://www.metaweather.com/"

    private fun getclient(): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(1200,TimeUnit.SECONDS).connectTimeout(1200,TimeUnit.SECONDS).build()
    }
    private fun getRetrofit():Retrofit{
        val client : OkHttpClient
        client = getclient()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getLocation(lat : Double, lon :Double) : LiveData <List<RepoLocation>>{
        val datas = MutableLiveData<List<RepoLocation>>()
        val term  = "$lat,$lon"
        val apiService : APIService = getRetrofit().create(APIService::class.java)
        val datalist : Call<List<RepoLocation>> = apiService.getLocation(term)
        datalist.enqueue(object : Callback<List<RepoLocation>> {
            override fun onResponse(
                call: Call<List<RepoLocation>>,
                response: Response<List<RepoLocation>>
            ) {
                if (response.isSuccessful){
                    datas.value = response.body()
                    Log.d("isSuccessful", "Location is Successful")
                    Log.e("isSuccessful", ""+response.body()!!.get(0).latt_long)
                }
            }
            override fun onFailure(call: Call<List<RepoLocation>>, t: Throwable) {
                datas.value = null
                Log.d("isFailure", "Location is Failure")
            }
        })
        return datas
    }

    fun getWeatherLocation(woeid : Int) :LiveData <RepoLoctionWeather>{
        val dataWeathers = MutableLiveData<RepoLoctionWeather>()
        val apiService : APIService = getRetrofit().create(APIService::class.java)
        val datalist : Call<RepoLoctionWeather> = apiService.getWeatherToWoeid(woeid.toString())
        datalist.enqueue(object : Callback<RepoLoctionWeather> {
            override fun onResponse(
                call: Call<RepoLoctionWeather>,
                response: Response<RepoLoctionWeather>
            ) {
                dataWeathers.value = response.body()
                Log.e("isSuccessful", "WeatherLocation is Successful")
                Log.e("Woeid number", " "+response.body()?.woeid)
            }

            override fun onFailure(call: Call<RepoLoctionWeather>, t: Throwable) {
                Log.e("isFailure", " "+t.message)
                dataWeathers.value = null
            }
        })
        return dataWeathers
    }

    fun getWeatherLocationToTime(woeid : Int, date : String) :LiveData <List<Consolidated_Weather>>{
        val datas = MutableLiveData<List<Consolidated_Weather>>()
        val apiService : APIService = getRetrofit().create(APIService::class.java)
        val datalist : Call<List<Consolidated_Weather>> = apiService.getWeatherToTime(woeid, date)
        datalist.enqueue(object : Callback<List<Consolidated_Weather>> {
            override fun onResponse(
                call: Call<List<Consolidated_Weather>>,
                response: Response<List<Consolidated_Weather>>
            ) {
                if(response.isSuccessful) {
                    datas.value = response.body()
                    Log.d("isSuccessful", "WeatherLocationToTime is Successful")
                    Log.d("First item id", "${response.body()?.get(0)?.id}")
                }
            }
            override fun onFailure(
                call: Call<List<Consolidated_Weather>>,
                t: Throwable
            ) {
                datas.value
                Log.d("isFailure", "WeatherLocationToTime is Failure")
            }
        })
        return datas
    }
}