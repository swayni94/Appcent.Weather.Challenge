package com.example.appcentweather.models

import com.google.gson.annotations.SerializedName


data class Parent (
                    @SerializedName("title")
                    val title : String?,
                    @SerializedName("location_type")
                    val location_type : String?,
                    @SerializedName("woeid")
                    val woeid : Long?,
                    @SerializedName("latt_long")
                    val latt_long : String?)
