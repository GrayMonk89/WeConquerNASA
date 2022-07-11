package com.gb.weconquernasa.view.ux

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import com.gb.weconquernasa.MainActivity
import com.gb.weconquernasa.R

@SuppressLint("CustomSplashScreen") // Android 12
class SplashActivity : AppCompatActivity() { //TODO HW single-activity пытаемся сохранить
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.BlueTheme)
        setContentView(R.layout.activity_splash)

//        ObjectAnimator.ofFloat(findViewById<ImageView>(R.id.imageView), View.ROTATION, 720f)
//            .setDuration(4000).start()
        findViewById<ImageView>(R.id.imageView).animate().rotationBy(720f).setDuration(4000).start()
        Handler(mainLooper).postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 3000)

    }
}