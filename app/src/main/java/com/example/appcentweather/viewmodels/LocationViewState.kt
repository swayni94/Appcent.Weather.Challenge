package com.example.appcentweather.viewmodels

import androidx.lifecycle.LiveData
import com.example.appcentweather.models.RepoLocation
import com.example.appcentweather.viewmodels.base.ViewState


class LocationViewState (
    var repoLocation : LiveData<List<RepoLocation>>? = null,
    var errorMessage: String = "",
    var enterBtnEnabled: Boolean = true,
): ViewState()