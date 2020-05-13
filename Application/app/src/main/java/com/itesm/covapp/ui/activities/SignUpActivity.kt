package com.itesm.covapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.itesm.covapp.R
import com.itesm.covapp.utils.Intents
import com.itesm.covapp.utils.Msn
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    private lateinit var database: DatabaseReference
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        database = FirebaseDatabase.getInstance().reference

        onClick()
    }

    private fun onClick(){
        btnJoin.setOnClickListener {
            registerUser()
        }
    }


    private fun registerUser(){
        // Database reference pointing to demo node
        val userName = txtUserName.text.toString()
        val lastName = txtLastName.text.toString()
        val email = txtMailUser.text.toString()
        val password = txtPassword.text.toString()
        val passwordConfirm = txtPassword.text.toString()

        if(email.isEmpty() || password.isEmpty()) {
            Msn.makeToast(this,"Por favor rellene todos los campos")
            return
        }

        if(password != passwordConfirm){
            Msn.makeToast(this,"Las contraseñas no coinciden")
            return
        }

        println(userName + lastName + email + password)

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(!it.isSuccessful) return@addOnCompleteListener
                //else
                val id = it.result?.user?.uid
                if (id != null) {
                    writeNewUser(id,userName,lastName)
                }
                Intents.goToHome(this)
                finish()
            }
            .addOnFailureListener {
                Msn.makeToast(this,"Error al crear usario, por favor verifique su conexión")
                return@addOnFailureListener
            }
    }

    private fun writeNewUser(userId: String, name: String, lastName:String) {
        database.child("users").child(userId).child("name").setValue(name)
        database.child("users").child(userId).child("lastName").setValue(lastName)
    }
}
