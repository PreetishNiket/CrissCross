package com.example.tictactoe

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.muddzdev.styleabletoast.StyleableToast
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.custom_layout.view.*
import kotlinx.android.synthetic.main.rate_custom_layout.view.*

class HomeActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        //val animDeep = AnimationUtils.loadAnimation(applicationContext, R.anim.deep)
        mediaPlayer= MediaPlayer.create(this,R.raw.button_pop)
        mediaPlayer.setVolume(10F,10F)
        mediaPlayer.isLooping=false

        multi.setOnClickListener {
            //multi.startAnimation(animDeep)
            startActivity(Intent(this,MainActivity::class.java))
            mediaPlayer.start()
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
        }

        vsAndroid.setOnClickListener {
           // vsAndroid.startAnimation(animDeep)
            mediaPlayer.start()
            startActivity(Intent(this,AndroidActivity::class.java))
            //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
        }
        rate_us_btn.setOnClickListener {
            //rate_us_btn.startAnimation(animDeep)
            mediaPlayer.start()
            val dialog= AlertDialog.Builder(this)
            val dialogView= LayoutInflater.from(this).inflate(R.layout.rate_custom_layout,null)
            dialog.setView(dialogView)
            val alertDialog=dialog.show()
            dialogView.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->

            }
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageManager")))

        }
        owner_info.setOnClickListener {
            //owner_info.startAnimation(animDeep)
            mediaPlayer.start()
            startActivity(Intent(this,OwnerActivity::class.java))
        }
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        // Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        StyleableToast.makeText(this,"Please click BACK again to exit", Toast.LENGTH_SHORT,R.style.exit).show()
    }

    override fun onResume() {
        super.onResume()
        doubleBackToExitPressedOnce = false
    }
}