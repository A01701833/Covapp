package com.itesm.covapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.itesm.covapp.R
import com.itesm.covapp.models.UserModel
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_menu.imgUserProfile

class ProfileActivity : AppCompatActivity()  {

    //FirebaseInstance
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        loadData()
        onClick()
    }

    private fun onClick(){
        imageView4.setOnClickListener {
            onBackPressed()
        }
    }

    private fun loadData(){
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val storage = FirebaseStorage.getInstance()
        database = FirebaseDatabase.getInstance().reference.child("users").child(uid)
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(item: DataSnapshot) {
                val user = item.getValue(UserModel::class.java)
                user?.let {
                    textView2.text = it.name+ " " + it.lastName
                }
            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })

        val ref = FirebaseStorage.getInstance().getReference("images").child(uid)
        ref.downloadUrl.addOnSuccessListener {
            Glide.with(this)
                .load(it)
                .into(UserProfile)
        }
    }
}
