package com.gb.weconquernasa.view.ux

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.weconquernasa.databinding.FragmentUxButtonBinding

class ButtonUXFragment : Fragment() {
    private var _binding: FragmentUxButtonBinding? = null
    private val binding: FragmentUxButtonBinding
        get() = _binding!!

    companion object{
        fun newInstance() = ButtonUXFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUxButtonBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}