package com.example.appcentweather.injection.component

import com.example.appcentweather.Application
import com.example.appcentweather.injection.module.*
import com.example.appcentweather.injection.scopes.AppScoped
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

//
@AppScoped
@Component(modules = [
    AppModule::class,
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class,
    RepositoryModule::class,
    ViewModelModule::class,
    ViewStatesModule::class
])
interface AppComponent: AndroidInjector<Application> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application) : Builder

        fun build():AppComponent
    }
}
