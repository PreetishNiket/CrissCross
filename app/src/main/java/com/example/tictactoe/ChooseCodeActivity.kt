package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ChooseCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_code)
        supportActionBar?.hide()
    }
}