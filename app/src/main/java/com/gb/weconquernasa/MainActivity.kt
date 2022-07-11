package com.gb.weconquernasa

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gb.weconquernasa.animations.AnimationsFragment
import com.gb.weconquernasa.databinding.ActivityMainBinding
import com.gb.weconquernasa.layout.constraint.ConstraintFragment
import com.gb.weconquernasa.layout.coordinator.CoordinatorFragment
import com.gb.weconquernasa.layout.motion.MotionFragment
import com.gb.weconquernasa.recycler.RecyclerFragment
import com.gb.weconquernasa.utils.*
import com.gb.weconquernasa.view.picture.PictureOfTheDayFragment
import com.gb.weconquernasa.view.ux.ButtonUXFragment
import com.gb.weconquernasa.view.ux.TextUXFragment
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {


    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getRealStyle(getCurrentTheme()))//R.style.BlueTheme
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {//PictureOfTheDayFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(
                R.id.mainContainer,
                ConstraintFragment.newInstance()
            ).commit()
        }
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
                    binding.fabExit.visibility = View.VISIBLE
                    navigationTo(PictureOfTheDayFragment())
                    true
                }
                R.id.actionBottomNavigationCoordinator -> {
                    binding.fabExit.visibility = View.GONE
                    navigationTo(CoordinatorFragment())
                    true
                }
                R.id.actionBottomNavigationMotion -> {
                    binding.fabExit.visibility = View.VISIBLE
                    navigationTo(ButtonUXFragment())
                    true
                }
                R.id.actionBottomNavigationAnimations -> {
                    binding.fabExit.visibility = View.VISIBLE
                    navigationTo(TextUXFragment())
                    true
                }
                R.id.actionBottomNavigationRecycler -> {
                    binding.fabExit.visibility = View.VISIBLE
                    navigationTo(RecyclerFragment())
                    true
                }

                else -> true
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.actionBottomNavigationMotion
    }

    private fun navigationTo(f: Fragment) {
        supportFragmentManager.beginTransaction().setCustomAnimations(
            R.anim.slide_in_left,
            R.anim.slide_out_right,

        ).replace(R.id.mainContainer, f).commit()
    }

    fun setCurrentTheme(currentTheme: Int) {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_CURRENT_THEME, currentTheme)
        editor.apply()
    }

    fun getCurrentTheme(): Int {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_CURRENT_THEME, -1)
    }

    private fun getRealStyle(currentTheme: Int): Int {
        return when (currentTheme) {
            THEME_ONE -> R.style.GreenTheme
            THEME_TWO -> R.style.RedTheme
            THEME_THREE -> R.style.BlueTheme
            else -> 0
        }
    }

}