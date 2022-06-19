package com.gb.weconquernasa.animations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentAnimationsBinding


class AnimationsFragment : Fragment() {
    private var _binding: FragmentAnimationsBinding? =null
    private val binding: FragmentAnimationsBinding
    get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = AnimationsFragment()
    }

}
