package com.example.appcentweather.until

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import dagger.internal.Preconditions


object ActivityUtils {
    fun addFragmentToActivity(fragmentManager: FragmentManager,
                              fragment: Fragment, frameId: Int) {
        Preconditions.checkNotNull(fragmentManager)
        Preconditions.checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }
}