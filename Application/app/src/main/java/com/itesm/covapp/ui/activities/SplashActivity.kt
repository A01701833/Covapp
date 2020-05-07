package com.itesm.covapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.itesm.covapp.R
import com.itesm.covapp.utils.Intents
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        supportActionBar?.hide()

        val seconds: Long = 3
        val time: Long = seconds*1000

        Timer().schedule(object: TimerTask() {
            override fun run() {
                Intents.goToWelcome(this@MainActivity)
                finish()
            }
        }, time)

    }
}
