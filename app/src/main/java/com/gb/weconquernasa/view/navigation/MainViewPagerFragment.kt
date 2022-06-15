package com.gb.weconquernasa.view.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gb.weconquernasa.databinding.FragmentMainViewPagerBinding
import com.gb.weconquernasa.view.navigation.viewpager.MainViewPagerAdapter

class MainViewPagerFragment : Fragment() {

    private var _binding: FragmentMainViewPagerBinding? = null
    private val binding: FragmentMainViewPagerBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainViewPagerBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainViewPager.adapter = MainViewPagerAdapter(requireActivity().supportFragmentManager)
    }

    companion object {

        @JvmStatic
        fun newInstance() = MainViewPagerFragment()
    }
}