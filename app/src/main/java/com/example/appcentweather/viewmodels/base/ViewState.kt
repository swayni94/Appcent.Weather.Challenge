package com.example.appcentweather.viewmodels.base

import android.os.Bundle
import kotlin.reflect.KClass


open class ViewState (
    var newActivity: KClass<*>? = null,
    var clearActivityOnIntent: Boolean = false,
    var intentExtras: Bundle = Bundle()
    )