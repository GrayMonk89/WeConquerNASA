package com.gb.weconquernasa.view.navigation

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.gb.weconquernasa.MainActivity
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentMainViewPagerBinding
import com.gb.weconquernasa.view.navigation.viewpager.MainViewPagerAdapter
import com.gb.weconquernasa.view.picture.BottomNavigationDrawerFragment
import com.gb.weconquernasa.view.settings.SettingsFragment
import com.google.android.material.bottomappbar.BottomAppBar
import kotlin.system.exitProcess

class MainViewPagerFragment : Fragment() {

    private var isMain = true

    private var _binding: FragmentMainViewPagerBinding? = null
    private val binding: FragmentMainViewPagerBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPagerAdapter()
        initFABListener()
        initMenuAppBar()
    }

    private fun initViewPagerAdapter() {
        binding.mainViewPager.adapter =
            MainViewPagerAdapter(requireActivity().supportFragmentManager)
    }

    private fun initFABListener() {
        binding.fab.setOnClickListener {
            if (isMain) {
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_back_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_empty)
            } else {
                binding.bottomAppBar.navigationIcon = (ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_hamburger_menu_bottom_bar
                ))
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_plus_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
            isMain = !isMain
        }
    }

    private fun initMenuAppBar() {
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionFavouriteAppBar -> {
            }
            R.id.actionSettingsAppBar -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.mainContainer, SettingsFragment.newInstance()).addToBackStack("")
                    .commit()
            }
            R.id.actionExitAppBar -> {
                exitProcess(0)
            }
            android.R.id.home -> {
                BottomNavigationDrawerFragment.newInstance()
                    .show(requireActivity().supportFragmentManager, "")
            }

        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        @JvmStatic
        fun newInstance() = MainViewPagerFragment()
    }
}