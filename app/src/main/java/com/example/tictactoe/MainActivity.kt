package com.example.tictactoe

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.muddzdev.styleabletoast.StyleableToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val buttons = Array(3) { arrayOfNulls<Button?>(3) }
    private var xturn = true
    private var roundcount = 0
    private var xpoints = 0
    private var opoints = 0
    private var drawPoints=0
    private var x1: TextView? = null
    private var o1: TextView? = null

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mp: MediaPlayer
    private lateinit var mp1: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
       // val animDeep = AnimationUtils.loadAnimation(applicationContext, R.anim.deep)

        mediaPlayer= MediaPlayer.create(this,R.raw.button_pop)
        mediaPlayer.isLooping=false
        mp= MediaPlayer.create(this,R.raw.buttontictactoe)
        mp.isLooping=false
        mp1= MediaPlayer.create(this,R.raw.button_win)
        mp1.isLooping=false

        x1 = findViewById(R.id.xwins)
        o1 = findViewById(R.id.owins)

        for (i in 0..2) {
            for (j in 0..2) {
                val buttonID = "button$i$j"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById<Button?>(resID)
                buttons[i][j]!!.setOnClickListener(this)
            }
        }
        val buttonReset: Button = findViewById(R.id.reset)
        buttonReset.setOnClickListener {

            resetGame()
            mediaPlayer.start()
        }

        resetBoard.setOnClickListener {
            resetBoard()
           //alertDialog
            mediaPlayer.start()
        }
    }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
    }

    override fun onClick(view: View) {
        when {
            (view as Button).text.toString() != "" -> {
                return
            }
            xturn -> {

                view.text = "X"
                mp.start()
                view.setTextColor(Color.parseColor("#ff0015"))

            }
            else -> {

                view.text = "O"
                mp.start()
                view.setTextColor(Color.parseColor("#fddb27ff"))
            }
        }
        roundcount++
        if (checkWin()) {
            if (xturn) {
                xWins()
            } else {
                oWins()
            }
        } else if (roundcount == 9) {
            draw()
        } else {
            xturn = !xturn
        }
    }

    private fun checkWin(): Boolean {
        val map = Array(3) { arrayOfNulls<String?>(3) }
        for (i in 0..2) {
            for (j in 0..2) {
                map[i][j] = buttons[i][j]!!.text.toString()
            }
        }
        for (i in 0..2) {
            if (map[i][0] == map[i][1] && map[i][0] == map[i][2] && map[i][0] != "") {
                return true
            }
            if (map[0][i] == map[1][i] && map[0][i] == map[2][i] && map[0][i] != "") {
                return true
            }
        }
        if (map[0][0] == map[1][1] && map[0][0] == map[2][2] && map[0][0] != "") {
            return true
        }
        if (map[0][2] == map[1][1] && map[0][2] == map[2][0] && map[0][2] != "") {
            return true
        }
        return false
    }

    private fun draw() {
        drawPoints++
        draw.text="Draw: $drawPoints"
        mp1.start()
        StyleableToast.makeText(this,"DRAW", Toast.LENGTH_SHORT,R.style.Draw).show()
        resetBoard()
    }

    private fun oWins() {
        opoints++
        StyleableToast.makeText(this,"O WON", Toast.LENGTH_SHORT,R.style.OWins).show()
        mp1.start()
        updatePoints()
        resetBoard()
    }

    private fun xWins() {
        xpoints++
        mp1.start()
        StyleableToast.makeText(this,"X WON", Toast.LENGTH_SHORT,R.style.xWins).show()
        updatePoints()
        resetBoard()
    }

    private fun resetBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j]!!.text = ""
            }
        }
        roundcount = 0
        xturn = true
    }

    private fun updatePoints() {
        if (xpoints==0)
        {
            x1!!.text = "X Wins:"
        }
        else
        {
            x1!!.text = "X Wins: $xpoints"
        }
        if (opoints==0)
        {
            o1!!.text = "O Wins:"
        }
        else{
            o1!!.text = "O Wins: $opoints"
        }
    }

    private fun resetGame() {
        xpoints = 0
        opoints = 0
        draw.text="Draw:"
        updatePoints()
        resetBoard()
    }
    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        StyleableToast.makeText(this,"Are You Sure You Want To Exit,Progress Will Get Lost", Toast.LENGTH_LONG,R.style.exit).show()
    }

    override fun onResume() {
        super.onResume()
        doubleBackToExitPressedOnce = false
    }
}