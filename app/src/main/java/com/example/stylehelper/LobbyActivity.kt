package com.example.stylehelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home.*

class LobbyActivity : AppCompatActivity() {

    //private var auth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        //---파이어베이스에서 사용자 정보를 넘겨주는 코드입니다.---
        
        //val user = Firebase.auth.currentUser
        //user?.let {
            // Name, email address, and profile photo Url
            //val name = user.displayName
            //val email = user.email
            //val photoUrl = user.photoUrl

            // Check if user's email is verified
            //val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            //val uid = user.uid

            //이메일을 넘겨줌
            //val info1 : TextView = findViewById(R.id.username)
            //info1.text = email

        //}
    }
//    var time3: Long = 0
//    override fun onBackPressed() {
//        val time1 = System.currentTimeMillis()
//        val time2 = time1 - time3
//        if (time2 in 0..2000) {
//            finish()
//        }
//        else {
//            time3 = time1
//            Toast.makeText(applicationContext, "한번 더 누르시면 종료됩니다.",Toast.LENGTH_SHORT).show()
//        }
//    }
}