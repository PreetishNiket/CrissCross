package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import com.muddzdev.styleabletoast.StyleableToast
import kotlinx.android.synthetic.main.activity_online.*

var isMyMove = isCodeMaker
class OnlineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_online)
        supportActionBar?.hide()
        reset.setOnClickListener {
//            reset()
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
//                    moveonline(data.toString(), isMyMove)
                } else {
                    isMyMove = true
//                    moveonline(data.toString(), isMyMove)
                }


            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
//                reset()
                StyleableToast.makeText(baseContext,"Game Reset",R.style.exit).show()
            }

        })
    }
    var player1Count = 0
    var player2Count = 0

}