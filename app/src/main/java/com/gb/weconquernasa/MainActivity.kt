package com.gb.weconquernasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gb.weconquernasa.view.picture.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.mainContainer,
                PictureOfTheDayFragment.newInstance()
            ).commit()
        }
    }
}