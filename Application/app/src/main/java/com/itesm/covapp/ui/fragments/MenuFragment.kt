package com.itesm.covapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.itesm.covapp.R
import com.itesm.covapp.models.UserModel
import com.itesm.covapp.utils.Intents
import com.itesm.covapp.utils.Msn
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment: Fragment() {

    //FirebaseInstance
    private lateinit var database: DatabaseReference

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

        loadData()
        onClick()
    }

    private fun onClick(){
        btnSettingsProfile.setOnClickListener {
            Intents.goToProfile(activity!!)
        }
        btnLogOut.setOnClickListener{ view ->
            Intents.goToLogin(activity!!)
            activity!!.finish()
            signOut()
        }
    }

    private fun loadData(){
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val storage = FirebaseStorage.getInstance()
        database = FirebaseDatabase.getInstance().reference.child("users").child(uid)
        database.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(item: DataSnapshot) {
                val user = item.getValue(UserModel::class.java)
                user?.let {
                    txtWelcome.text = "¡Hola ${it.name}!"
                    txtLocation.text = "Tu ubicación: ${it.state}"
                }
            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })

        val ref = FirebaseStorage.getInstance().getReference("images").child(uid)
        ref.downloadUrl.addOnSuccessListener {
            Glide.with(activity!!)
                .load(it)
                .into(imgUserProfile)
        }
    }

    private fun signOut(){
        FirebaseAuth.getInstance().signOut()
    }

}