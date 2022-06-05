package com.example.stylehelper.subFragments.daily

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.stylehelper.R
import com.example.stylehelper.databinding.ActivityDailyBoardInsideBinding
import com.example.stylehelper.utils.FBRef
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_daily_board_inside.*


class DailyBoardInsideActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDailyBoardInsideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_board_inside)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_daily_board_inside)

        var key = intent.getStringExtra("key").toString()
        getBoardData(key)
        getImageData(key)

        binding.likebtn.setOnClickListener {
            onStarClicked(FBRef.dailyboardRef.child(key))
        }

    }

    private fun getBoardData(key: String) {
        val user = Firebase.auth.currentUser
        user?.let {
            val uid = user.uid
            val postListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val dataModel = dataSnapshot.getValue(DailyBoardModel::class.java)
                    binding.titleArea.text = dataModel!!.title
                    binding.timeArea.text = dataModel!!.time
                    binding.contentArea.text = dataModel!!.content
                    binding.likeArea.text = "likecount: " + dataModel!!.likecount

                    if (dataModel.likes.containsKey(uid)) {
                        // Unstar the post and remove self from stars
                        likebtn.setImageResource(R.drawable.like)
                    } else {
                        // Star the post and add self to stars
                        likebtn.setImageResource(R.drawable.like2)
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            }
            //FBRef에서 데이터를 가져온다.
            FBRef.dailyboardRef.child(key).addValueEventListener(postListener)
        }
    }

    private fun getImageData(key: String) {
        val storageReference = Firebase.storage.reference.child(key + ".png")

        // ImageView in your Activity
        val imageView = binding.imgArea

        storageReference.downloadUrl.addOnCompleteListener{ task ->
            if(task.isSuccessful) {
                Glide.with(this).load(task.result).into(imageView)
            }
        }
    }

    private fun onStarClicked(postRef: DatabaseReference) {

        val user = Firebase.auth.currentUser
        user?.let {
            val uid = user.uid
            postRef.runTransaction(object : Transaction.Handler {
                override fun doTransaction(mutableData: MutableData): Transaction.Result {
                    val dataModel = mutableData.getValue(DailyBoardModel::class.java)
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

                }
            })
        }
    }
}