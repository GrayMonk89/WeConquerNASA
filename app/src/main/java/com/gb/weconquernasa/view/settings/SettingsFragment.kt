package com.gb.weconquernasa.view.settings

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.gb.weconquernasa.MainActivity
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentSettingsBinding
import com.gb.weconquernasa.utils.*
import com.google.android.material.chip.ChipGroup
import com.google.android.material.tabs.TabLayout

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() = _binding!!

    private lateinit var parentActivity : MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = requireActivity() as MainActivity
    }

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
        setThemeChoice()
        setDayNightChoice()
    }

    private fun setDayNightChoice() {
        binding.switchTheme.setOnCheckedChangeListener { view, isChecked ->
            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun setThemeChoice(){
        when (parentActivity.getCurrentTheme()) {
            1 -> binding.chipGroupMenu.check(R.id.chipOneMenuOne)
            2 -> binding.chipGroupMenu.check(R.id.chipTwoMenuOne)
            3 -> binding.chipGroupMenu.check(R.id.chipThreeMenuOne)
        }
        binding.chipGroupMenu.setOnCheckedStateChangeListener { chipGroup: ChipGroup, mutableList: MutableList<Int> ->
            for (id in mutableList) {
                when (id) {
                    R.id.chipOneMenuOne -> {
                        parentActivity.setCurrentTheme(THEME_ONE)
                        parentActivity.recreate()
                    }
                    R.id.chipTwoMenuOne -> {
                        parentActivity.setCurrentTheme(THEME_TWO)
                        parentActivity.recreate()
                    }
                    R.id.chipThreeMenuOne -> {
                        parentActivity.setCurrentTheme(THEME_THREE)
                        parentActivity.recreate()
                    }
                }
            }
        }
    }

    private fun initTabsListener() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    when (it.position) {
                        DEFAULT_VALUE_ZERO -> {
                            binding.chipGroupMenu.visibility = View.VISIBLE
                            binding.chipGroupMenuTwo.visibility = View.GONE
                            binding.chipGroupMenuThree.visibility = View.GONE
                        }
                        DEFAULT_VALUE_ONE -> {
                            binding.chipGroupMenu.visibility = View.GONE
                            binding.chipGroupMenuTwo.visibility = View.VISIBLE
                            binding.chipGroupMenuThree.visibility = View.GONE
                        }
                        DEFAULT_VALUE_TWO -> {
                            binding.chipGroupMenu.visibility = View.GONE
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