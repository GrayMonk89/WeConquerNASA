package com.gb.weconquernasa.view.navigation.viewpager

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.gb.weconquernasa.utils.DEFAULT_VALUE_ONE
import com.gb.weconquernasa.utils.DEFAULT_VALUE_ZERO
import com.gb.weconquernasa.utils.LOG_KEY
import com.gb.weconquernasa.view.navigation.SpaceViewPagerFragment
import com.gb.weconquernasa.view.picture.PictureOfTheDayFragment

class MainViewPagerAdapter(private val fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {

            DEFAULT_VALUE_ZERO -> {
                Log.d(LOG_KEY, position.toString())
                PictureOfTheDayFragment.newInstance()
            }

            DEFAULT_VALUE_ONE -> {
                Log.d(LOG_KEY, position.toString())
                SpaceViewPagerFragment.newInstance()
            }

            else -> {
                Log.d(LOG_KEY, position.toString())
                PictureOfTheDayFragment.newInstance()
            }
        }


    }
}