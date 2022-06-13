package com.gb.weconquernasa.view.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentEarthBinding
import com.gb.weconquernasa.databinding.FragmentSolSystemBinding

class EarthFragment : Fragment() {

    private var _binding: FragmentEarthBinding? = null
    private val binding: FragmentEarthBinding = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarthBinding.inflate(inflater,container,false)
        return binding.root
    }

    companion object {
        fun newInstance() = EarthFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}