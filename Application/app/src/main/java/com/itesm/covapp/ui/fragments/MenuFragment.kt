package com.itesm.covapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.itesm.covapp.R
import com.itesm.covapp.models.UserModel
import com.itesm.covapp.utils.Intents
import com.itesm.covapp.utils.Msn
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment: Fragment() {

    //UserInfo
    var userNameGlobal:String = " "

    //FirebaseInstance
    private lateinit var userReference: DatabaseReference

    companion object {
        fun newInstance(): MenuFragment{
            val frag = MenuFragment()

            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        loadInfo(uid)
        onClick()
    }

    private fun onClick(){
        btnSettingsProfile.setOnClickListener {
            Intents.goToProfile(activity!!)
        }
    }

    private fun loadInfo(id:String){
        userReference = FirebaseDatabase.getInstance().reference
            .child("users")
            .child(id)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val post = dataSnapshot.getValue(UserModel::class.java)
                // [START_EXCLUDE]
                post?.let {
                    userNameGlobal = it.username.toString()
                    txtWelcome.text = "¡Hola ${it.username.toString()}!"
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
            }
        }
        userReference.addValueEventListener(postListener)
    }

    fun logOut(){

    }
}