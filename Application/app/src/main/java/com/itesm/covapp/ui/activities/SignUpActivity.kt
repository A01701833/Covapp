package com.itesm.covapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itesm.covapp.R
import com.itesm.covapp.utils.Intents
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        onClick()
    }

    private fun onClick(){
        btnJoin.setOnClickListener {
            Intents.goToHome(this@SignUpActivity)
        }
    }
}
