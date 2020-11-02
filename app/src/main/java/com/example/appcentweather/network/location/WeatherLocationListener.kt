package com.example.appcentweather.network.location

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.appcentweather.injection.scopes.AppScoped
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


@AppScoped
class WeatherLocationListener  {
    val LOCATION_REFRESH_TIME = 10000
    val LOCATION_REFRESH_DISTANCE = 30

    @Inject
    fun startListiningUserLocation(context: Context, userLocationListener: UserLocationListener) {
        val locationManager = context.getSystemService(DaggerAppCompatActivity.LOCATION_SERVICE) as LocationManager
        val locationListener: LocationListener = object : LocationListener {

            override fun onLocationChanged(location: Location) {
                userLocationListener.onLocationChange(location)
            }

            override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}
            override fun onProviderEnabled(p0: String?) {}
            override fun onProviderDisabled(p0: String?) {}
        }
        @Suppress("DEPRECATED_IDENTITY_EQUALS")
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !== PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            } else { ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            }
        }else {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                LOCATION_REFRESH_TIME.toLong(),
                LOCATION_REFRESH_DISTANCE.toFloat(),
                locationListener
            )
        }
    }

    interface UserLocationListener{
        fun onLocationChange(location: Location):Boolean
    }
}