package com.example.tictactoe

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_owner.*

class OwnerActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owner)
        supportActionBar?.hide()
        val animation2= AnimationUtils.loadAnimation(this,R.anim.fade)
            textView4.startAnimation(animation2)

        mediaPlayer= MediaPlayer.create(this,R.raw.coffin)
        mediaPlayer.setVolume(10F,10F)
        mediaPlayer.isLooping=false
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}