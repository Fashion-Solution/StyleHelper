package com.example.stylehelper.utils

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FBRef {

    companion object{
        private val database = Firebase.database

        val dailyboardRef = database.getReference("Daily")
        val communityboardRef = database.getReference("community")
    }
}