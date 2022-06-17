package com.gb.weconquernasa

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.gb.weconquernasa.databinding.ActivityMainBinding
import com.gb.weconquernasa.layout.LayoutFragment
import com.gb.weconquernasa.layout.fragments.ConstraintFragment
import com.gb.weconquernasa.layout.fragments.CoordinatorFragment
import com.gb.weconquernasa.layout.fragments.MotionFragment
import com.gb.weconquernasa.utils.*
import com.gb.weconquernasa.view.navigation.MainViewPagerFragment
import com.gb.weconquernasa.view.picture.BottomNavigationDrawerFragment
import com.gb.weconquernasa.view.settings.SettingsFragment
import com.google.android.material.bottomappbar.BottomAppBar
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
        setTheme(getRealStyle(getCurrentTheme()))
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
        supportFragmentManager.beginTransaction().replace(R.id.mainContainer, f).commit()
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