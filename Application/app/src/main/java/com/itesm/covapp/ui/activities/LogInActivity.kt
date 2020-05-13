package com.itesm.covapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.itesm.covapp.R
import com.itesm.covapp.utils.Intents
import com.itesm.covapp.utils.Msn
import com.itesm.covapp.utils.PreferencesManager
import com.itesm.covapp.utils.Utils
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.pop_loading_progress.*

class LogInActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance();
    private lateinit var manager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        supportActionBar?.hide()
        manager = PreferencesManager(this)
        val utils = Utils(this)
        permissions(utils)
        onClick()
    }

    private fun permissions(utils: Utils){
        utils.permission()
    }

    private fun onClick(){
        btnLogIn.setOnClickListener {
            progress_loader.visibility = View.VISIBLE
            val mail = txtUserMailLogin.text.toString().decapitalize()
            val password = txtuserPassowrdLogIn.text.toString()
            login(mail, password)
        }

        linearLayout2.setOnClickListener {
            Intents.goToSignUp(this)
            finish()
        }
    }

    private fun login (email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            this.mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener ( this, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                            Intents.goToHome(this)
                            finish()
                    } else {
                        progress_loader.visibility = View.INVISIBLE
                        Msn.makeToast(this,"Error Logging in :(")
                    }
                })
        }else {
            progress_loader.visibility = View.INVISIBLE
            Msn.makeToast(this,"Please fill up the Credentials :|")
        }
    }
}
