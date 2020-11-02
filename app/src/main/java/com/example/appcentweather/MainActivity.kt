package com.example.appcentweather

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.appcentweather.fragment.LocationFragment
import com.example.appcentweather.network.location.WeatherLocationListener
import com.example.appcentweather.until.ActivityUtils
import com.example.appcentweather.until.AppConstants
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject



class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var  locationFragment: LocationFragment
    @Inject
    lateinit var weatherLocationListener: WeatherLocationListener

    lateinit var _location : Location
    var term : Boolean =false
    lateinit var timer:CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timer = object: CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                weatherLocationListener.startListiningUserLocation(this@MainActivity, object : WeatherLocationListener.UserLocationListener{
                    override fun onLocationChange(location: Location):Boolean {
                        _location = location
                        _location.longitude
                        Log.e("location" , ""+_location)
                        @Suppress("SENSELESS_COMPARISON")
                        when{ _location !=null -> term = true }
                        return term
                    }
                })
            }

            override fun onFinish() {
                Log.e("Fragment" , "Fragment is Start")
                var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                if (fragment == null) {
                    fragment = locationFragment
                    ActivityUtils.addFragmentToActivity(
                        supportFragmentManager,
                        fragment,
                        R.id.contentFrame
                    )
                }
                val prefences = getSharedPreferences("WeatherApplication", MODE_PRIVATE)
                val editor = prefences.edit()
                editor.putFloat(AppConstants.LOCATION_LATITUDE, _location.latitude.toFloat())
                editor.putFloat(AppConstants.LOCATION_LONGITUDE, _location.longitude.toFloat())
                editor.apply()
            }
        }

        Log.e("Start", "start = true")
        timer.start()

    }

    @Suppress("DEPRECATED_IDENTITY_EQUALS")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this@MainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION) === PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                       // getLocationListener()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}