package com.gb.weconquernasa.view.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentSpaceViewPagerBinding
import com.gb.weconquernasa.view.navigation.viewpager.SpaceViewPagerAdapter

class SpaceViewPagerFragment : Fragment() {

    private var _binding: FragmentSpaceViewPagerBinding? = null
    private val binding: FragmentSpaceViewPagerBinding
    get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSpaceViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        setIcon()
    }

    private fun initViewPager() {
        binding.spaceViewPager.adapter =
            SpaceViewPagerAdapter(requireActivity().supportFragmentManager)
        binding.tabLayoutSpace.setupWithViewPager(binding.spaceViewPager)
    }

    private fun setIcon() {
        binding.tabLayoutSpace.getTabAt(0)?.setIcon(R.drawable.ic_earth)
        binding.tabLayoutSpace.getTabAt(1)?.setIcon(R.drawable.ic_system)
        binding.tabLayoutSpace.getTabAt(2)?.setIcon(R.drawable.ic_mars)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SpaceViewPagerFragment()
    }
}