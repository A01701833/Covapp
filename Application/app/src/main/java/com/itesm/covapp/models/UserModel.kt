package com.itesm.covapp.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserModel(
    var id:String? ="",
    var username: String? = "",
    var email: String? = "",
    var about:String? = "",
    var wordAboutYou:String? =""
)