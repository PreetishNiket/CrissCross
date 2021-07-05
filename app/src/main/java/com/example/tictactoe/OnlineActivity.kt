package com.example.tictactoe

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.*
import com.muddzdev.styleabletoast.StyleableToast
import kotlinx.android.synthetic.main.activity_online.*
import kotlin.system.exitProcess

var isMyMove = isCodeMaker
var playerTurn = true

class OnlineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_online)
        supportActionBar?.hide()
        reset.setOnClickListener {
            reset()
        }
        FirebaseDatabase.getInstance().reference.child("data").child(code)
            .addChildEventListener(object : ChildEventListener {
                override fun onCancelled(error: DatabaseError) {}
                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val data = snapshot.value
                    if (isMyMove) {
                        isMyMove = false
                        moveOnline(data.toString(), isMyMove)
                    } else {
                        isMyMove = true
                        moveOnline(data.toString(), isMyMove)
                    }
                }
                override fun onChildRemoved(snapshot: DataSnapshot) {
                    reset()
                    StyleableToast.makeText(baseContext, "Game Reset", R.style.exit).show()
                }

            })
    }

    private var player1Count = 0
    private var player2Count = 0
    fun onClick(view: View) {
        if (isMyMove) {
            val but = view as Button

            val cellOnline = when (but.id) {
                R.id.button11 -> 1
                R.id.button12 -> 2
                R.id.button13 -> 3
                R.id.button14 -> 4
                R.id.button15 -> 5
                R.id.button16 -> 6
                R.id.button17 -> 7
                R.id.button18 -> 8
                R.id.button19 -> 9
                else -> {
                    0
                }

            }
            playerTurn = false
            Handler(Looper.myLooper()!!).postDelayed({ playerTurn = true }, 600)
            playNow(but, cellOnline)
            updateDatabase(cellOnline)

        } else {
            Toast.makeText(this, "Wait for your turn", Toast.LENGTH_LONG).show()
        }
    }

    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()
    private var emptyCells = ArrayList<Int>()
    private var activeUser = 1
    private fun playNow(buttonSelected: Button, currCell: Int) {
        val audio = MediaPlayer.create(this, R.raw.button_pop)
        buttonSelected.text = "X"
        emptyCells.remove(currCell)
        turn.text = "Turn : Player 2"
        buttonSelected.setTextColor(Color.parseColor("#EC0C0C"))
        player1.add(currCell)
        emptyCells.add(currCell)
        audio.start()
        buttonSelected.isEnabled = false
        Handler(Looper.myLooper()!!).postDelayed({ audio.release() }, 200)
        checkWinner()
    }

    fun moveOnline(data: String, move: Boolean) {
        val audio = MediaPlayer.create(this, R.raw.button_pop)

        if (move) {
            val buttonSelected: Button? = when (data.toInt()) {
                1 -> button11
                2 -> button12
                3 -> button13
                4 -> button14
                5 -> button15
                6 -> button16
                7 -> button17
                8 -> button18
                9 -> button19
                else -> {
                    button11
                }
            }
            buttonSelected?.text = "O"
            turn.text = "Turn : Player 1"
            buttonSelected?.setTextColor(Color.parseColor("#D22BB804"))
            player2.add(data.toInt())
            emptyCells.add(data.toInt())
            audio.start()
            Handler(Looper.myLooper()!!).postDelayed({ audio.release() }, 200)
            buttonSelected?.isEnabled = false
            checkWinner()
        }
    }

    private fun updateDatabase(cellId: Int) {
        FirebaseDatabase.getInstance().reference.child("data").child(code).push().setValue(cellId)
    }

    private fun checkWinner(): Int {
        val audio = MediaPlayer.create(this, R.raw.button_win)
        if ((player1.contains(1) && player1.contains(2) && player1.contains(3)) || (player1.contains(
                1
            ) && player1.contains(4) && player1.contains(7)) ||
            (player1.contains(3) && player1.contains(6) && player1.contains(9)) || (player1.contains(
                7
            ) && player1.contains(8) && player1.contains(9)) ||
            (player1.contains(4) && player1.contains(5) && player1.contains(6)) || (player1.contains(
                1
            ) && player1.contains(5) && player1.contains(9)) ||
            player1.contains(3) && player1.contains(5) && player1.contains(7) || (player1.contains(2) && player1.contains(
                5
            ) && player1.contains(8))
        ) {
            player1Count += 1
            buttonDisable()
            audio.start()
            disableReset()
            Handler(Looper.myLooper()!!).postDelayed({ audio.release() }, 4000)
            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("Player 1 Wins!!" + "\n\n" + "Do you want to play again")
            build.setPositiveButton("Ok") { _, _ ->
                reset()
                audio.release()
            }
            build.setNegativeButton("Exit") { _, _ ->
                audio.release()
                removeCode()
                exitProcess(1)

            }
            Handler(Looper.myLooper()!!).postDelayed({ build.show() }, 2000)
            return 1


        } else if ((player2.contains(1) && player2.contains(2) && player2.contains(3)) || (player2.contains(
                1
            ) && player2.contains(4) && player2.contains(7)) ||
            (player2.contains(3) && player2.contains(6) && player2.contains(9)) || (player2.contains(
                7
            ) && player2.contains(8) && player2.contains(9)) ||
            (player2.contains(4) && player2.contains(5) && player2.contains(6)) || (player2.contains(
                1
            ) && player2.contains(5) && player2.contains(9)) ||
            player2.contains(3) && player2.contains(5) && player2.contains(7) || (player2.contains(2) && player2.contains(
                5
            ) && player2.contains(8))
        ) {
            player2Count += 1
            audio.start()
            buttonDisable()
            disableReset()
            Handler(Looper.myLooper()!!).postDelayed({ audio.release() }, 4000)
            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("Player 2 Wins!!" + "\n\n" + "Do you want to play again")
            build.setPositiveButton("Ok") { _, _ ->
                reset()
                audio.release()
            }
            build.setNegativeButton("Exit") { _, _ ->
                audio.release()
                removeCode()
                exitProcess(1)
            }
            Handler(Looper.myLooper()!!).postDelayed({ build.show() }, 2000)
            return 1
        } else if (emptyCells.contains(1) && emptyCells.contains(2) && emptyCells.contains(3) && emptyCells.contains(
                4
            ) && emptyCells.contains(5) && emptyCells.contains(6) && emptyCells.contains(7) &&
            emptyCells.contains(8) && emptyCells.contains(9)
        ) {

            val build = AlertDialog.Builder(this)
            build.setTitle("Game Draw")
            build.setMessage("Nobody Wins" + "\n\n" + "Do you want to play again")
            build.setPositiveButton("Ok") { _, _ ->
                audio.release()
                reset()
            }
            build.setNegativeButton("Exit") { _, _ ->
                audio.release()
                removeCode()
                exitProcess(1)

            }
            build.show()
            return 1

        }
        return 0
    }

    fun reset() {
        player1.clear()
        player2.clear()
        emptyCells.clear()
        activeUser = 1
        for (i in 1..9) {
            val buttonSelected: Button? = when (i) {
                1 -> button11
                2 -> button12
                3 -> button13
                4 -> button14
                5 -> button15
                6 -> button16
                7 -> button17
                8 -> button18
                9 -> button19
                else -> {
                    button11
                }
            }
            buttonSelected?.isEnabled = true
            buttonSelected?.text = ""
            p1.text = "Player1 : $player1Count"
            p2.text = "Player2 : $player2Count"
            isMyMove = isCodeMaker
            if (isCodeMaker) {
                FirebaseDatabase.getInstance().reference.child("data").child(code).removeValue()
            }
        }
    }

    private fun buttonDisable() {
        for (i in 1..9) {
            val buttonSelected = when (i) {
                1 -> button11
                2 -> button12
                3 -> button13
                4 -> button14
                5 -> button15
                6 -> button16
                7 -> button17
                8 -> button18
                9 -> button19
                else -> {
                    button11
                }

            }
            if (buttonSelected.isEnabled)
                buttonSelected.isEnabled = false
        }
    }

    private fun removeCode() {
        if (isCodeMaker) {
            FirebaseDatabase.getInstance().reference.child("code").child(keyValue).removeValue()
        }
    }

    private fun disableReset() {
        reset.isEnabled = false
        Handler(Looper.myLooper()!!).postDelayed({ reset.isEnabled = true }, 2200)
    }

//    fun buttonCellDisable() {
//        emptyCells.forEach {
//            val buttonSelected = when (it) {
//                1 -> button11
//                2 -> button12
//                3 -> button13
//                4 -> button14
//                5 -> button15
//                6 -> button16
//                7 -> button17
//                8 -> button18
//                9 -> button19
//                else -> {
//                    button11
//                }
//
//            }
//            if (buttonSelected.isEnabled)
//                buttonSelected.isEnabled = false
//        }
//    }

    override fun onBackPressed() {
        removeCode()
        if (isCodeMaker) {
            FirebaseDatabase.getInstance().reference.child("data").child(code).removeValue()
        }
        exitProcess(0)
    }
}