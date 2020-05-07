package com.itesm.covapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.itesm.covapp.R
import com.itesm.covapp.utils.Intents
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        supportActionBar?.hide()

        onClick()
    }

    private fun onClick(){
        btnLogIn.setOnClickListener {
            Intents.goToHome(this@LogInActivity)
        }

        linearLayout2.setOnClickListener {
            Intents.goToSignUp(this)
        }
    }
}
