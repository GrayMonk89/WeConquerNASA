package com.gb.weconquernasa.view.settings

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentSettingsBinding
import com.gb.weconquernasa.utils.DEFAULT_VALUE_ONE
import com.gb.weconquernasa.utils.DEFAULT_VALUE_TWO
import com.gb.weconquernasa.utils.DEFAULT_VALUE_ZERO
import com.google.android.material.tabs.TabLayout

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabsListener()

    }

    private fun initTabsListener() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    when (it.position) {
                        DEFAULT_VALUE_ZERO -> {
                            binding.chipGroupMenuOne.visibility = View.VISIBLE
                            binding.chipGroupMenuTwo.visibility = View.GONE
                            binding.chipGroupMenuThree.visibility = View.GONE
                        }
                        DEFAULT_VALUE_ONE -> {
                            binding.chipGroupMenuOne.visibility = View.GONE
                            binding.chipGroupMenuTwo.visibility = View.VISIBLE
                            binding.chipGroupMenuThree.visibility = View.GONE
                        }
                        DEFAULT_VALUE_TWO -> {
                            binding.chipGroupMenuOne.visibility = View.GONE
                            binding.chipGroupMenuTwo.visibility = View.GONE
                            binding.chipGroupMenuThree.visibility = View.VISIBLE
                        }
                        else -> {}
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}