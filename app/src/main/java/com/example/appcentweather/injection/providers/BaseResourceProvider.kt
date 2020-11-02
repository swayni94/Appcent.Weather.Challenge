package com.example.appcentweather.injection.providers

import androidx.annotation.NonNull
import androidx.annotation.StringRes


interface BaseResourceProvider {
    @NonNull
    fun getString(@StringRes id : Int) : String

    @NonNull
    fun getString(@StringRes resId : Int, formatArgs : Any) : String
}