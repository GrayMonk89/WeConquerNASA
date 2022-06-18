package com.gb.weconquernasa.layout.coordinator

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.ImageLoader
import coil.load
import com.gb.weconquernasa.MainActivity
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentCoordinatorBinding
import kotlin.system.exitProcess

class CoordinatorFragment : Fragment() {

    private var _binding:FragmentCoordinatorBinding? = null
    private val binding:FragmentCoordinatorBinding
    get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoordinatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFABLongClickListener()
    }

    private fun initFABLongClickListener() {
        binding.fabOnScrollView.setOnLongClickListener {
            exitProcess(0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = CoordinatorFragment()
    }
}