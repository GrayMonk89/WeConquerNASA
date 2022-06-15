package com.gb.weconquernasa.view.picture

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentBottomNavigationLayoutBinding
import com.gb.weconquernasa.utils.LOG_KEY
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomNavigationLayoutBinding? = null
    private val binding: FragmentBottomNavigationLayoutBinding
        get() = _binding!!


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomNavigationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_first ->{
                    Log.d(LOG_KEY,it.title.toString())
                    dismiss()
                }
                R.id.navigation_second ->{
                    Log.d(LOG_KEY,it.title.toString())
                    dismiss()
                }
            }
            true
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BottomNavigationDrawerFragment()
    }
}