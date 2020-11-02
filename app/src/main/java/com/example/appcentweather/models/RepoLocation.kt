package com.example.appcentweather.models


data class RepoLocation(val distance : Int,
                        val title : String,
                        val location_type : String,
                        val woeid : Int,
                        val latt_long : String)
