package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.muddzdev.styleabletoast.StyleableToast
import kotlinx.android.synthetic.main.activity_choose_code.*

var isCodeMaker = true
var code = "null"
var codeFound = false
var checkTemp = true
var keyValue = "null"

class ChooseCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_code)
        supportActionBar?.hide()
        create.setOnClickListener {
            code = "null";
            codeFound = false
            checkTemp = true
            keyValue = "null"

            code=gameCode.text.toString()
            progressBar.visibility= View.VISIBLE
            if(code!="null" && code !=""){
                isCodeMaker=true
            FirebaseDatabase.getInstance().reference.child("code")
                .addValueEventListener(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val checkCode = isCodeAvailable(snapshot, code)
                        Handler().postDelayed({
                            if (checkCode) {
                                progressBar.visibility = View.GONE
                                StyleableToast.makeText(baseContext,"Room is full or Deleted",R.style.exit).show()
                            } else {
                                FirebaseDatabase.getInstance().reference.child("code").push()
                                    .setValue(code)
                                isCodeAvailable(snapshot, code)
                                checkTemp = false
                                Handler().postDelayed({
                                    matched()
                                    StyleableToast.makeText(baseContext,"Please don't go back",R.style.exit).show()
                                }, 300)

                            }
                        }, 2000)
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })
            }else{
                progressBar.visibility = View.GONE
                StyleableToast.makeText(baseContext,"Enter The Code Properly",R.style.exit).show()
            }
        }

        join.setOnClickListener {
            code = "null"
            codeFound = false
            checkTemp = true
            keyValue = "null"
            code = gameCode.text.toString()
            if(code!="null"&& code!=""){
                progressBar.visibility=View.VISIBLE
                isCodeMaker=false
                FirebaseDatabase.getInstance().reference.child("code")
                    .addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(error: DatabaseError) {}

                        override fun onDataChange(snapshot: DataSnapshot) {
                            val data: Boolean = isCodeAvailable(snapshot, code)

                            Handler().postDelayed({
                                if (data) {
                                    codeFound = true
                                    matched()
                                    progressBar.visibility = View.GONE
                                } else {
                                    progressBar.visibility = View.GONE
                                    StyleableToast.makeText(baseContext,"Invalid Code",R.style.exit).show()
                                }
                            }, 2000)
                        }
                    })

            }else{
                StyleableToast.makeText(baseContext,"Enter Code Properly",R.style.exit).show()
            }
        }
    }
    private fun matched() {
        startActivity(Intent(this, OnlineActivity::class.java));
        progressBar.visibility = View.GONE
    }
    private fun isCodeAvailable(snapshot: DataSnapshot, code: String): Boolean {
        val data = snapshot.children
        data.forEach {
            val value = it.value.toString()
            if (value == code) {
                keyValue = it.key.toString()
                return true
            }
        }
        return false
    }
}