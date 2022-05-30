package com.example.stylehelper.board

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
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
import kotlinx.android.synthetic.main.activity_board_inside.*


class BoardInsideActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBoardInsideBinding
    private var auth : FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_inside)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_inside)

        binding.likebtn.setOnClickListener {

        }

        var key = intent.getStringExtra("key").toString()
        getBoardData(key)
        getImageData(key)

        likebtn.setOnClickListener {
            onStarClicked(FBRef.boardRef)
        }
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

    private fun onStarClicked(postRef: DatabaseReference) {
        val uid = ""
        postRef.runTransaction(object : Transaction.Handler {
            override fun doTransaction(mutableData: MutableData): Transaction.Result {
                val dataModel = mutableData.getValue(BoardModel::class.java)
                    ?: return Transaction.success(mutableData)

                if (dataModel.likes.containsKey(uid)) {
                    // Unstar the post and remove self from stars
                    dataModel.likecount = dataModel.likecount - 1
                    dataModel.likes.remove(uid)
                } else {
                    // Star the post and add self to stars
                    dataModel.likecount = dataModel.likecount + 1
                    dataModel.likes[uid] = true
                }

                // Set value and report transaction success
                mutableData.value = dataModel
                return Transaction.success(mutableData)
            }

            override fun onComplete(
                databaseError: DatabaseError?,
                committed: Boolean,
                currentData: DataSnapshot?
            ) {
                // Transaction completed
                Log.d(TAG, "postTransaction:onComplete:" + databaseError!!)
            }
        })
    }

 /*   private fun onClickLike(key: String) {
        var postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var dataModel = dataSnapshot.getValue(BoardModel::class.java)

                if (dataModel!!.likes.containsKey(*//*현재 사용자의 uid 주소*//*)) {
                    dataModel!!.likecount -= 1
                    FBRef.boardRef.child(key).addValueEventListener(postListener)

                } else {
                    dataModel!!.likecount += 1
                    FBRef.boardRef.child(key).child("likes").(postListener)
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        FBRef.boardRef.child(key).addValueEventListener(postListener)
    }*/
}