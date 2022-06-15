package com.gb.weconquernasa.view.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentApiBottomBinding
import com.gb.weconquernasa.view.navigation.space.EarthFragment
import com.gb.weconquernasa.view.navigation.space.MarsFragment
import com.gb.weconquernasa.view.navigation.space.SolSystemFragment
import com.google.android.material.badge.BadgeDrawable

class ApiBottomFragment : Fragment() {

    private var _binding: FragmentApiBottomBinding? = null
    private val binding: FragmentApiBottomBinding
        get() = _binding!!
    private var click: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApiBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomNavigationListener()
        setBadgeBottomNavigationEarth()
    }

    private fun setBadgeBottomNavigationEarth() {
        val badge = binding
            .bottomNavigation
            .getOrCreateBadge(R.id.actionSpaceBottomNavigationEarth)
        badge.number = click
        badge.maxCharacterCount = 2
        badge.badgeGravity = BadgeDrawable.TOP_END
        badge.backgroundColor = ContextCompat.getColor(requireContext(), R.color.purple_200)
        badge.badgeTextColor = ContextCompat.getColor(requireContext(), R.color.white)
    }

    private fun initBottomNavigationListener() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.actionSpaceBottomNavigationEarth -> {
                    click++
                    binding.bottomNavigation.getOrCreateBadge(R.id.actionSpaceBottomNavigationEarth).number =
                        click
                    if (click >= 10){
                        binding.bottomNavigation.removeBadge(R.id.actionSpaceBottomNavigationEarth)
                    }
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.container, EarthFragment.newInstance()).commit()
                    true
                }
                R.id.actionSpaceBottomNavigationSystem -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SolSystemFragment.newInstance()).commit()
                    true
                }
                R.id.actionSpaceBottomNavigationMars -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MarsFragment.newInstance()).commit()
                    true
                }
                else -> {
                    true
                }
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.actionSpaceBottomNavigationEarth
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ApiBottomFragment()
    }
}