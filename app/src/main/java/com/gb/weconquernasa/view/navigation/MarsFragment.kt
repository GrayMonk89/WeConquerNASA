package com.gb.weconquernasa.view.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentMarsBinding
import com.gb.weconquernasa.databinding.FragmentSolSystemBinding

class MarsFragment : Fragment() {

    private var _binding: FragmentMarsBinding? = null
    private val binding: FragmentMarsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsBinding.inflate(inflater,container,false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MarsFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}