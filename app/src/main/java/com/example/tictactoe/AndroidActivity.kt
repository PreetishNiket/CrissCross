package com.example.tictactoe

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_android.*

class AndroidActivity : AppCompatActivity(), View.OnClickListener {
    private var buttons = arrayOfNulls<Button>(3)
    private var playerTurn = true
    private var roundcount = 0
    private var playerPoints = 0
    private var androidPoints = 0
    private var drawPoints = 0
    private var x1: TextView? = null
    private var o1: TextView? = null

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mp: MediaPlayer
    private lateinit var mp1: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android)
        supportActionBar?.hide()
        //sounds
        mediaPlayer = MediaPlayer.create(this, R.raw.button_pop)
        mediaPlayer.isLooping = false

        mp = MediaPlayer.create(this, R.raw.buttontictactoe)
        mp.isLooping = false

        mp1 = MediaPlayer.create(this, R.raw.button_win)
        mp1.isLooping = false

        x1 = findViewById(R.id.playerWins)
        o1 = findViewById(R.id.androidWins)

        for (i in buttons.indices) {
            val buttonID = "button$i"
            val resID = resources.getIdentifier(buttonID, "id", packageName)
            buttons[i]=findViewById<Button?>(resID)
            buttons[i]?.setOnClickListener(this)


        }
        val buttonReset: Button = findViewById(R.id.reset1)
        buttonReset.setOnClickListener {
            mediaPlayer.start()
        }
        resetBoard1.setOnClickListener {
            //resetBoard()

            mediaPlayer.start()
        }
    }//onCreate


    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun onClick(v: View?) {
        when {
            (v as Button).text.toString() != "" -> {
                return
            }
            playerTurn->{

                v.text = "X"
                mp.start()
                v.setTextColor(Color.parseColor("#ff0015"))
            }
            else->{

            }
        }
    }
}
//data class BtnCell(val i:Int, val j:Int)