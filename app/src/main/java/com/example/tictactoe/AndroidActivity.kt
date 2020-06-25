package com.example.tictactoe

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.muddzdev.styleabletoast.StyleableToast
import kotlinx.android.synthetic.main.activity_android.*
import java.text.FieldPosition
import kotlin.random.Random

class AndroidActivity : AppCompatActivity() {
    //private var buttons = arrayOfNulls<Button>(3)
    var board = arrayListOf<String>("", "", "", "", "", "", "", "", "")

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mp: MediaPlayer
    private lateinit var mp1: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android)
        supportActionBar?.hide()
       // val animDeep = AnimationUtils.loadAnimation(applicationContext, R.anim.deep)
        //sounds
        mediaPlayer = MediaPlayer.create(this, R.raw.button_pop)
        mediaPlayer.isLooping = false

        mp = MediaPlayer.create(this, R.raw.buttontictactoe)
        mp.isLooping = false

        mp1 = MediaPlayer.create(this, R.raw.button_win)
        mp1.isLooping = false
        button0.setOnClickListener {
            if (board[0]=="")
            {
                button0.text="X"
                button0.setTextColor(Color.parseColor("#ff0015"))
                mp.start()
                board[0]="X"
                if (!isBoardFull(board)&&!result(board,"X"))
                {
                    val position=getAndroidMove(board)
                    board[position]="O"
                    Handler().postDelayed({
                        displayAndroidButton(position)
                    },600)

                }
            }
            resultOperation(board)
        }
        button1.setOnClickListener {
            if (board[1]=="")
            {
                button1.text="X"
                button1.setTextColor(Color.parseColor("#ff0015"))
                mp.start()
                board[1]="X"
                if (!isBoardFull(board)&&!result(board,"X"))
                {
                    val position=getAndroidMove(board)
                    board[position]="O"
                    Handler().postDelayed({
                        displayAndroidButton(position)
                    },600)
                }
            }
            resultOperation(board)
        }
        button2.setOnClickListener {
            if (board[2]=="")
            {
                button2.text="X"
                    button2.setTextColor(Color.parseColor("#ff0015"))
                mp.start()
                board[2]="X"
                if (!isBoardFull(board)&&!result(board,"X"))
                {
                    val position=getAndroidMove(board)
                    board[position]="O"
                    Handler().postDelayed({
                        displayAndroidButton(position)
                    },600)
                }
            }
            resultOperation(board)
        }
        button3.setOnClickListener {
            if (board[3]=="")
            {
                button3.text="X"
                button3.setTextColor(Color.parseColor("#ff0015"))
                mp.start()
                board[3]="X"
                if (!isBoardFull(board)&&!result(board,"X"))
                {
                    val position=getAndroidMove(board)
                    board[position]="O"
                    Handler().postDelayed({
                        displayAndroidButton(position)
                    },600)
                }
            }
            resultOperation(board)
        }
        button4.setOnClickListener {
            if (board[4]=="")
            {
                button4.text="X"
                button4.setTextColor(Color.parseColor("#ff0015"))
                mp.start()
                board[4]="X"
                if (!isBoardFull(board)&&!result(board,"X"))
                {
                    val position=getAndroidMove(board)
                    board[position]="O"
                    Handler().postDelayed({
                        displayAndroidButton(position)
                    },600)
                }
            }
            resultOperation(board)
        }
        button5.setOnClickListener {
            if (board[5]=="")
            {
                button5.text="X"
                button5.setTextColor(Color.parseColor("#ff0015"))
                mp.start()
                board[5]="X"
                if (!isBoardFull(board)&&!result(board,"X"))
                {
                    val position=getAndroidMove(board)
                    board[position]="O"
                    Handler().postDelayed({
                        displayAndroidButton(position)
                    },600)
                }
            }
            resultOperation(board)
        }
        button6.setOnClickListener {
            if (board[6]=="")
            {
                button6.text="X"
                button6.setTextColor(Color.parseColor("#ff0015"))
                mp.start()
                board[6]="X"
                if (!isBoardFull(board)&&!result(board,"X"))
                {
                    val position=getAndroidMove(board)
                    board[position]="O"
                    Handler().postDelayed({
                        displayAndroidButton(position)
                    },600)
                }
            }
            resultOperation(board)
        }
        button7.setOnClickListener {
            if (board[7]=="")
            {
                button7.text="X"
                button7.setTextColor(Color.parseColor("#ff0015"))
                mp.start()
                board[7]="X"
                if (!isBoardFull(board)&&!result(board,"X"))
                {
                    val position=getAndroidMove(board)
                    board[position]="O"
                    Handler().postDelayed({
                        displayAndroidButton(position)
                    },600)
                }
            }
            resultOperation(board)
        }
        button8.setOnClickListener {
            if (board[8] == "") {
                button8.text = "X"
                button8.setTextColor(Color.parseColor("#ff0015"))
                mp.start()
                board[8] = "X"
                if (!isBoardFull(board) && !result(board, "X")) {
                    val position = getAndroidMove(board)
                    board[position] = "O"
                    Handler().postDelayed({
                        displayAndroidButton(position)
                    },600)
                }
            }
            resultOperation(board)
        }
        val buttonReset: Button = findViewById(R.id.reset1)
        buttonReset.setOnClickListener {
            //buttonReset.startAnimation(animDeep)
            startActivity(Intent(this@AndroidActivity,AndroidActivity::class.java))
            mediaPlayer.start()
        }
        resetBoard1.setOnClickListener {
            //resetBoard1.startAnimation(animDeep)
            resetBoard()
            mediaPlayer.start()
        }
    }//onCreate
    private fun resetBoard() {
        for (i in 0 until board.size)
        {
            board[i]=""
        }
        button0.text=""
        button1.text=""
        button2.text=""
        button3.text=""
        button4.text=""
        button5.text=""
        button6.text=""
        button7.text=""
        button8.text=""
    }

    private fun getAndroidMove(board: ArrayList<String>):Int{

        //Android Win
        for (i in 0 until board.size)
        {
            val copy=getBoardCopy(board)
            if (copy[i]==""){
                copy[i]="O"
            }
            if (result(copy,"O"))
            {
                return i
            }
        }
        //Player Win
        for (i in 0 until board.size){
            val copy2=getBoardCopy(board)
            if (copy2[i]==""){
                copy2[i]="X"
            }
            if (result(copy2,"X"))
            {
                return i
            }
        }
        val move=chooseRandomMove(board, arrayListOf(0,2,6,8))
        if (move!=-1){
            return move
        }
        if (board[4]==""){
            return 4
        }
        return chooseRandomMove(board, arrayListOf(1,3,5,7))
    }

    private fun chooseRandomMove(board: ArrayList<String>, list: ArrayList<Int>):Int{
        val possibleMove= arrayListOf<Int>()
        for (i in list)
        {
            if (board[i]=="")
            {
                possibleMove.add(i)
            }
        }
                return if (possibleMove.isEmpty()) {
            -1
        } else {
            val index= Random.nextInt(possibleMove.size)
            possibleMove[index]
        }

    }

    private fun displayAndroidButton(position: Int) {
        //sound also
        when (position) {
            0 ->
            {
                button0.text="O"
                button0.setTextColor(Color.parseColor("#fddb27ff"))
            }
            1 ->
            {
                button1.text = "O"
                button1.setTextColor(Color.parseColor("#fddb27ff"))
            }
            2 ->{
                button2.text = "O"
                button2.setTextColor(Color.parseColor("#fddb27ff"))
            }
            3 ->
            {
                button3.text = "O"
                button3.setTextColor(Color.parseColor("#fddb27ff"))
            }
            4 ->
            {
                button4.text = "O"
                button4.setTextColor(Color.parseColor("#fddb27ff"))
            }
            5 ->
            {
                button5.text = "O"
                button5.setTextColor(Color.parseColor("#fddb27ff"))
            }
            6 ->
            {
                button6.text = "O"
                button6.setTextColor(Color.parseColor("#fddb27ff"))
            }
            7 ->
            {
                button7.text = "O"
                button7.setTextColor(Color.parseColor("#fddb27ff"))
            }
            8 ->
            {
                button8.text = "O"
                button8.setTextColor(Color.parseColor("#fddb27ff"))
            }
        }


    }

     private fun getBoardCopy(board: ArrayList<String>): ArrayList<String> {
        val bdCopy = arrayListOf<String>("", "", "", "", "", "", "", "", "")
        for (i in 0 until board.size){
            bdCopy[i]=board[i]
        }
            return bdCopy
    }

    private fun isBoardFull(board: ArrayList<String>): Boolean {
        for (i in board) {
            if (i != "X" && i != "O") {
                return false
            }
        }
        return true
    }

    private var playerPoints=0
    private var androidPoints=0
    private var drawPoints=0
     private fun resultOperation(board: ArrayList<String>){
        if (result(board,"X"))
        {
            playerPoints++
            playerWins.text="Player Wins:\n${playerPoints}"
            mp1.start()
            StyleableToast.makeText(this,"YOU WON", Toast.LENGTH_SHORT,R.style.xWins).show()
            resetBoard()
        }
        if (result(board,"O"))
        {
            androidPoints++
            androidWins.text="Android Wins:\n${androidPoints}"
            mp1.start()
            StyleableToast.makeText(this,"ANDROID WON", Toast.LENGTH_SHORT,R.style.OWins).show()
            resetBoard()
        }
        if (isBoardFull(board))
        {
            drawPoints++
            draw.text="Draw Wins:${drawPoints}"
            mp1.start()
            StyleableToast.makeText(this,"DRAW", Toast.LENGTH_SHORT,R.style.Draw).show()
            resetBoard()
        }
    }
    //Check For Win
    private fun result(bdCopy:ArrayList<String>,s:String):Boolean {
        if (bdCopy[0] == s && bdCopy[1] == s && bdCopy[2] == s) {
            return true
        }
        else if(bdCopy[3] == s && bdCopy[4] == s && bdCopy[5] == s) {
            return true
        }
        else if(bdCopy[6] == s && bdCopy[7] == s && bdCopy[8] == s) {
            return true
        }
        else if(bdCopy[0] == s && bdCopy[3] == s && bdCopy[6] == s) {
            return true
        }
        else if(bdCopy[1] == s && bdCopy[4] == s && bdCopy[7] == s){
            return true
        }
        else if(bdCopy[2] == s && bdCopy[5] == s && bdCopy[8] == s){
            return true
        }
        else if(bdCopy[0] == s && bdCopy[4] == s && bdCopy[8] == s){
            return true
        }
        else if(bdCopy[2] == s && bdCopy[4] == s && bdCopy[6] == s){
            return true
        }
        return false
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            startActivity(Intent(this@AndroidActivity,HomeActivity::class.java))
            finish()

        }
        if (!doubleBackToExitPressedOnce)
        {
            StyleableToast.makeText(this,"Are You Sure You Want To Exit\nProgress Will Get Lost", Toast.LENGTH_LONG,R.style.exit).show()
            doubleBackToExitPressedOnce = true
        }

    }

    override fun onResume() {
        super.onResume()
        doubleBackToExitPressedOnce = false
    }
}