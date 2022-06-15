package com.gb.weconquernasa.view.navigation.viewpager

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.gb.weconquernasa.utils.DEFAULT_VALUE_ONE
import com.gb.weconquernasa.utils.DEFAULT_VALUE_TWO
import com.gb.weconquernasa.utils.DEFAULT_VALUE_ZERO
import com.gb.weconquernasa.utils.LOG_KEY
import com.gb.weconquernasa.view.navigation.ApiBottomFragment
import com.gb.weconquernasa.view.navigation.SpaceViewPagerFragment
import com.gb.weconquernasa.view.picture.PictureOfTheDayFragment

class MainViewPagerAdapter(private val fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 3
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

            DEFAULT_VALUE_TWO -> {
                ApiBottomFragment.newInstance()
            }

            else -> {
                Log.d(LOG_KEY, position.toString())
                PictureOfTheDayFragment.newInstance()
            }
        }


    }
}