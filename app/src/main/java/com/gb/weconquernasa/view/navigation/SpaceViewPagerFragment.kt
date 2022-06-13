package com.gb.weconquernasa.view.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.spaceViewPager.adapter = SpaceViewPagerAdapter(requireActivity().supportFragmentManager)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SpaceViewPagerFragment()
    }
}