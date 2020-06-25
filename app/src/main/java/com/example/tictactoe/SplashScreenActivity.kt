package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        val animation= AnimationUtils.loadAnimation(this,R.anim.zoomicon)
        imageView.startAnimation(animation)
        val animation2= AnimationUtils.loadAnimation(this,R.anim.fade)
        imageView2.startAnimation(animation2)

        Handler().postDelayed({

            startActivity(Intent(this,HomeActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
            finish()

        },1000)
    }
}