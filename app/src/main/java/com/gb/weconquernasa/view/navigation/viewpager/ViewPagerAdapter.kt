package com.gb.weconquernasa.view.navigation.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.gb.weconquernasa.utils.EARTH_DEFAULT_VALUE
import com.gb.weconquernasa.utils.SOLAR_SYSTEM_DEFAULT_VALUE
import com.gb.weconquernasa.view.navigation.EarthFragment
import com.gb.weconquernasa.view.navigation.MarsFragment
import com.gb.weconquernasa.view.navigation.SolSystemFragment

class ViewPagerAdapter(private val fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = arrayOf(
        EarthFragment.newInstance(),
        SolSystemFragment.newInstance(),
        MarsFragment.newInstance()
    )

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            EARTH_DEFAULT_VALUE -> {
                EarthFragment.newInstance()
            }
            SOLAR_SYSTEM_DEFAULT_VALUE -> {
                SolSystemFragment.newInstance()
            }
            else -> {
                MarsFragment.newInstance()
            }
        }
    }
}