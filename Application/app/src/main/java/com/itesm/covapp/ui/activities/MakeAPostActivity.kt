package com.itesm.covapp.ui.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.itesm.covapp.R
import com.itesm.covapp.models.UserModel
import kotlinx.android.synthetic.main.activity_make_a_post.*
import kotlinx.android.synthetic.main.pop_up_success_save.view.*
import java.util.*

class MakeAPostActivity : AppCompatActivity() {

    var topic: String = ""
    var state: String = ""
    var username:String = ""
    var lastname:String = ""
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_a_post)
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        loadUserData(uid)
        database = FirebaseDatabase.getInstance().reference.child("post")
        spinnerAdapter()
        onClick()
    }

    private fun onClick(){
        btnSavePost.setOnClickListener {
            val title = txtTitle.text.toString()
            val postContent = txtPostContent.text.toString()
            savePost(username,lastname,state,title,topic,postContent)
        }
    }

    private fun spinnerAdapter(){
        //  Adapter States
        val adapterTopic = ArrayAdapter.createFromResource(this,R.array.topics,android.R.layout.simple_spinner_item)
        adapterTopic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTopic.adapter = adapterTopic

        spinnerTopic.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // either one will work as well
                topic = adapterTopic.getItem(position) as String
            }
        }

        val adapterStates = ArrayAdapter.createFromResource(this,R.array.states,android.R.layout.simple_spinner_item)
        adapterStates.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerStates.adapter = adapterStates

        spinnerStates.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // either one will work as well
                state = adapterStates.getItem(position) as String
            }
        }
    }

    private fun loadUserData(uid: String) {
        database = FirebaseDatabase.getInstance().reference.child("users").child(uid)
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(item: DataSnapshot) {
                val user = item.getValue(UserModel::class.java)
                user?.let {
                    username = it.name.toString()
                    lastname = it.lastName.toString()
                }
            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    private fun savePost(
        name: String,
        lastName: String,
        state: String,
        title: String,
        topic: String,
        postContent: String
    ) {
        val idPost = UUID.randomUUID().toString()
        database.child(state).child(idPost).child("username").setValue(name)
        database.child(state).child(idPost).child("lastName").setValue(lastName)
        database.child(state).child(idPost).child("topic").setValue(topic)
        database.child(state).child(idPost).child("state").setValue(state)
        database.child(state).child(idPost).child("title").setValue(title)
        database.child(state).child(idPost).child("postContent").setValue(postContent)
            .addOnSuccessListener {
                showAlertDialog()
            }
    }


    private fun showAlertDialog(){
        val dialogBuilder = AlertDialog.Builder(this)
        val view = this.layoutInflater.inflate(R.layout.pop_up_success_save,null)
        dialogBuilder.setView(view)

        val alertDialog = dialogBuilder.create()
        alertDialog.show()

        view.btnOkSuccess.setOnClickListener {
            onBackPressed()
        }
    }
}
