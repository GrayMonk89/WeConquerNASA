package com.gb.weconquernasa.view.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentApiBottomBinding
import com.gb.weconquernasa.view.navigation.space.EarthFragment
import com.gb.weconquernasa.view.navigation.space.MarsFragment
import com.gb.weconquernasa.view.navigation.space.SolSystemFragment

class ApiBottomFragment : Fragment() {

    private var _binding: FragmentApiBottomBinding? = null
    private val binding: FragmentApiBottomBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApiBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.actionSpaceBottomNavigationEarth -> {
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

    companion object {
        @JvmStatic
        fun newInstance() = ApiBottomFragment()
    }
}