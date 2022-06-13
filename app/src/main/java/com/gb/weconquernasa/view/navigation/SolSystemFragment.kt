package com.gb.weconquernasa.view.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentSolSystemBinding


class SolSystemFragment : Fragment() {

    private var _binding: FragmentSolSystemBinding? = null
    private val binding: FragmentSolSystemBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSolSystemBinding.inflate(inflater,container,false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SolSystemFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}