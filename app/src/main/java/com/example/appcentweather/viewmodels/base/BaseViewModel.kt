package com.example.appcentweather.viewmodels.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appcentweather.network.restapi.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


abstract class BaseViewModel <S : ViewState>(val retrofitRepository: Repository, var viewState: S) : ViewModel(){
    protected val stateLiveData = MutableLiveData<ViewState>()

    private val networkJob = Job()
    protected val networkScope = CoroutineScope(Dispatchers.IO + networkJob)


    fun getState(): MutableLiveData<ViewState> {
        return this.stateLiveData
    }


    fun resetNewActivity() {
        viewState.newActivity = null
        updateUi()
    }

    fun updateUi() {
        stateLiveData.postValue(viewState)
    }
}