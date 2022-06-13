package com.gb.weconquernasa

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.gb.weconquernasa.databinding.ActivityMainBinding
import com.gb.weconquernasa.utils.*
import com.gb.weconquernasa.view.navigation.MainViewPagerFragment
import com.gb.weconquernasa.view.picture.BottomNavigationDrawerFragment
import com.gb.weconquernasa.view.settings.SettingsFragment
import com.google.android.material.bottomappbar.BottomAppBar
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private var isMain = true

    private var _binding: ActivityMainBinding? = null
    private val binding:ActivityMainBinding
    get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(getRealStyle(getCurrentTheme()))
        setContentView(binding.root)
        if (savedInstanceState == null) {//PictureOfTheDayFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(
                R.id.mainContainer,
                MainViewPagerFragment.newInstance()
            ).commit()
        }
        initMenuAppBar()
        initFABListener()    }

    private fun initFABListener() {
        binding.fab.setOnClickListener {
            if (isMain) {
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_back_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_empty)
            } else {
                binding.bottomAppBar.navigationIcon = (ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_hamburger_menu_bottom_bar
                ))
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_plus_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
            isMain = !isMain
        }
    }

    private fun initMenuAppBar() {
        this.setSupportActionBar(binding.bottomAppBar)
        //setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bottom_bar, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionFavouriteAppBar -> {
                //view?.showSnackBar(item.title.toString(), "", {}, Snackbar.LENGTH_SHORT)
            }
            R.id.actionSettingsAppBar -> {
                //view?.showSnackBar(item.title.toString(), "", {}, Snackbar.LENGTH_SHORT)
                this.supportFragmentManager.beginTransaction()
                    .add(R.id.mainContainer, SettingsFragment.newInstance()).addToBackStack("")
                    .commit()
            }
            R.id.actionExitAppBar -> {
                exitProcess(0)
            }
            android.R.id.home -> {
                BottomNavigationDrawerFragment.newInstance()
                    .show(this.supportFragmentManager, "")
            }

        }
        return super.onOptionsItemSelected(item)
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