package com.gb.weconquernasa.layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentLayoutBinding
import com.gb.weconquernasa.layout.fragments.ConstraintFragment
import com.gb.weconquernasa.layout.fragments.CoordinatorFragment
import com.gb.weconquernasa.layout.fragments.MotionFragment
import kotlin.system.exitProcess

class LayoutFragment : Fragment() {

    private var _binding: FragmentLayoutBinding? = null
    private val binding: FragmentLayoutBinding
    get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFABListener()
        initBottomNavigationView()
    }

    private fun setFABListener() {
        binding.fabExit.setOnClickListener() {
            exitProcess(0)
        }
    }

    private fun initBottomNavigationView() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.actionBottomNavigationConstraint -> {
                    navigationTo(ConstraintFragment())
                    true
                }
                R.id.actionBottomNavigationCoordinator -> {
                    navigationTo(CoordinatorFragment())
                    true
                }
                R.id.actionBottomNavigationMotion -> {
                    navigationTo(MotionFragment())
                    true
                }
                else -> true
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.actionBottomNavigationConstraint
    }

    private fun navigationTo(f: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container, f).commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = LayoutFragment()
    }
}