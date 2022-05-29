package com.example.stylehelper.board

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.stylehelper.R
import com.example.stylehelper.databinding.ActivityBoardInsideBinding
import com.example.stylehelper.utils.FBRef
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage


class BoardInsideActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBoardInsideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_inside)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_inside)

        binding.likebtn.setOnClickListener {

        }

        var key = intent.getStringExtra("key")
        getBoardData(key.toString())
        getImageData(key.toString())
    }

    private fun getBoardData(key: String) {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val dataModel = dataSnapshot.getValue(BoardModel::class.java)
                binding.titleArea.text = dataModel!!.title
                binding.timeArea.text = dataModel!!.time
                binding.contentArea.text = dataModel!!.content
                binding.likeArea.text = "likecount: " + dataModel!!.likecount
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        //FBRef에서 데이터를 가져온다.
        FBRef.boardRef.child(key).addValueEventListener(postListener)
    }

    private fun getImageData(key: String) {
        val storageReference = Firebase.storage.reference.child(key + ".png")

        // ImageView in your Activity
        val imageView = binding.imgArea

        storageReference.downloadUrl.addOnCompleteListener{ task ->
            if(task.isSuccessful) {
                Glide.with(this).load(task.result).into(imageView)
            } else {

            }
        }
    }
}