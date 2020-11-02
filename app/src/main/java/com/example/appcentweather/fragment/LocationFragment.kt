package com.example.appcentweather.fragment

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcentweather.R
import com.example.appcentweather.adapter.LocationsViewAdapter
import com.example.appcentweather.adapter.interfacehelper.RecyclerViewClickListener
import com.example.appcentweather.injection.scopes.ActivityScoped
import com.example.appcentweather.models.RepoLocation
import com.example.appcentweather.until.AppConstants
import com.example.appcentweather.viewmodels.LocationViewModel
import com.example.appcentweather.viewmodels.LocationViewState
import com.example.appcentweather.viewmodels.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_location.*
import javax.inject.Inject
//
@ActivityScoped
class LocationFragment @Inject constructor(override var viewModel:LocationViewModel)
    :BaseFragment<LocationViewModel, LocationViewState>(viewModel), RecyclerViewClickListener {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.viewModel = ViewModelProviders.of(this, viewModelFactory).get(LocationViewModel::class.java)

        val term = 0.0f
        val preference = activity?.getSharedPreferences("WeatherApplication", MODE_PRIVATE)
        val lat = preference?.getFloat(AppConstants.LOCATION_LATITUDE, term)?.toDouble()!!
        val lon = preference.getFloat(AppConstants.LOCATION_LONGITUDE, term).toDouble()
        Log.e("lat", ""+lat)
        Log.e("lon", ""+lon)

        viewModel.getLocation(lat, lon)
    }

    override fun attachClickListeners() {

    }

    override fun getLayoutResourceFile(): Int {
        return R.layout.fragment_location
    }

    override fun updateUi(state: LocationViewState) {
        state.repoLocation?.observe(this, Observer {
            setListAdapter(it)
        })

        state.newActivity?.start(state.clearActivityOnIntent, state.intentExtras)
    }

    private fun setListAdapter(locations:List<RepoLocation>){
        val adapter = LocationsViewAdapter(locations,this)
        locations_recyclerView.layoutManager = LinearLayoutManager(activity)
        locations_recyclerView.adapter = adapter
    }

    override fun onItemClickListener(view: View, location: RepoLocation) {
        Toast.makeText(activity, "${location.woeid}", Toast.LENGTH_SHORT).show()
        viewModel.launchWeatherActivity(location.woeid)
    }
}