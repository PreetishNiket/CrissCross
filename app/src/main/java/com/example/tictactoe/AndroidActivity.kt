package com.example.tictactoe

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
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
                board[0]="X"
                if (!isBoardFull(board)&&!result(board,"X"))
                {
                    val position=getAndroidMove(board)
                    board[position]="O"
                    displayAndroidButton(position)
                }
            }

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
    private fun getAndroidMove(board: ArrayList<String>):Int{

        //Android Win
        for (i in 0 until board.size)
        {
            var copy=getBoardCopy(board)
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
            var copy2=getBoardCopy(board)
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
        var possibleMove= arrayListOf<Int>()
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
    fun displayAndroidButton(position: Int)
    {
        //sound also
        when (position) {
            0 -> button0.text="O"
            1 -> button1.text = "O"
            2 -> button2.text = "O"
            3 -> button3.text = "O"
            4 -> button4.text = "O"
            5 -> button5.text = "O"
            6 -> button6.text = "O"
            7 -> button7.text = "O"
            8 -> button8.text = "O"
        }


    }
     private fun getBoardCopy(board: ArrayList<String>): ArrayList<String> {
        var bdCopy = arrayListOf<String>("", "", "", "", "", "", "", "", "")
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
    fun resultOperation(board: ArrayList<String>){
        if (result(board,"X"))
        {

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
}