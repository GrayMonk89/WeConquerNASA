package com.gb.weconquernasa.recycler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.weconquernasa.databinding.FragmentRecyclerBinding
import com.gb.weconquernasa.utils.*

class RecyclerFragment : Fragment() {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding: FragmentRecyclerBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = arrayListOf(
            Data(SUN_DEFAULT_NAME,SUN_DEFAULT_DESCRIPTION,SUN_DEFAULT_VALUE),
            Data(EARTH_DEFAULT_NAME,EARTH_DEFAULT_DESCRIPTION,EARTH_DEFAULT_VALUE),
            Data(SUN_DEFAULT_NAME,SUN_DEFAULT_DESCRIPTION,SUN_DEFAULT_VALUE),
            Data(EARTH_DEFAULT_NAME,EARTH_DEFAULT_DESCRIPTION,EARTH_DEFAULT_VALUE),
            Data(EARTH_DEFAULT_NAME,EARTH_DEFAULT_DESCRIPTION, EARTH_DEFAULT_VALUE),
            Data(MARS_DEFAULT_NAME, MARS_DEFAULT_DESCRIPTION, MARS_DEFAULT_VALUE),
            Data(EARTH_DEFAULT_NAME,EARTH_DEFAULT_DESCRIPTION,EARTH_DEFAULT_VALUE),
            Data(SUN_DEFAULT_NAME,SUN_DEFAULT_DESCRIPTION,SUN_DEFAULT_VALUE),
            Data(EARTH_DEFAULT_NAME,EARTH_DEFAULT_DESCRIPTION,EARTH_DEFAULT_VALUE),
            Data(MARS_DEFAULT_NAME, EARTH_DEFAULT_DESCRIPTION,MARS_DEFAULT_VALUE)
        )


        binding.recyclerView.adapter = RecyclerFragmentAdapter(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecyclerFragment()
    }
}