package com.example.appcentweather.viewmodels

import com.example.appcentweather.MainActivity
import com.example.appcentweather.fragment.LocationFragment
import com.example.appcentweather.injection.providers.BaseResourceProvider
import com.example.appcentweather.injection.providers.ResourceProvider
import com.example.appcentweather.injection.scopes.ActivityScoped
import com.example.appcentweather.injection.scopes.FragmentScoped
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


@Module(includes = [LocationModule.LocationAbstractModule::class])
class LocationModule {

    @ActivityScoped
    @Provides
    internal fun provideResourceProvider(context: MainActivity) : BaseResourceProvider{
        return ResourceProvider(context)
    }

    @Module
    interface LocationAbstractModule{
        @FragmentScoped
        @ContributesAndroidInjector
        fun locationFragment() : LocationFragment
    }
}