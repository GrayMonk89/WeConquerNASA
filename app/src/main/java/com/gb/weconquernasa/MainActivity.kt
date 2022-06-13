package com.gb.weconquernasa

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.gb.weconquernasa.utils.*
import com.gb.weconquernasa.view.navigation.MainViewPagerFragment
import com.gb.weconquernasa.view.picture.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getRealStyle(getCurrentTheme()))
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {//PictureOfTheDayFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(
                R.id.mainContainer,
                MainViewPagerFragment.newInstance()
            ).commit()
        }
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