package com.example.appcentweather.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
//
@Module
class ApplicationModule constructor(_context: Context) {
    private var context : Context
    init {
        this.context = _context
    }
    @Provides
    fun providesContext() : Context = context
}