package com.example.appcentweather.injection.providers

import android.content.Context
import androidx.annotation.NonNull
import dagger.internal.Preconditions



class ResourceProvider (@NonNull private val context: Context) : BaseResourceProvider {

    private val mContext : Context

    init {
        mContext = Preconditions.checkNotNull(context, "context cannot be null")
    }

    override fun getString(id: Int): String {
        return mContext.getString(id)
    }

    override fun getString(resId: Int, formatArgs: Any): String {
        return mContext.getString(resId, formatArgs)
    }
}