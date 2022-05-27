package com.example.stylehelper.board

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import com.bumptech.glide.Glide
import com.example.stylehelper.ListViewAdapter
import com.example.stylehelper.R
import com.example.stylehelper.utils.FBRef
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class BoardListActivity : AppCompatActivity() {


    private var TAG = BoardListActivity::class.java.simpleName
    lateinit var LVAdapter : ListViewAdapter
    val list = mutableListOf<BoardModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_list)

        val writeBtn = findViewById<Button>(R.id.writeBtn)
        writeBtn.setOnClickListener{
            val intent = Intent(this, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        //리스트 뷰 생성
        LVAdapter = ListViewAdapter(list)
        val lv = findViewById<ListView>(R.id.lv)
        lv.adapter=LVAdapter

        getFBBoardData()

        /*val key = intent.getStringExtra("key")
        getImageData(key.toString())*/
    }

    private fun getFBBoardData(){
        val postListener = object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                //리스트 새로고침
                list.clear()

                for(dataModel in dataSnapshot.children){
                    val item = dataModel.getValue(BoardModel::class.java)
                    list.add(item!!)
                }
                //리스트 재동기화
                LVAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        //FBRef에서 데이터를 가져온다.
        FBRef.boardRef.addValueEventListener(postListener)
    }

    private fun getImageData(key : String){
        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child(key+".png")

        // ImageView in your Activity
        val imageViewFromRB = findViewById<ImageView>(R.id.img)

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if(task.isSuccessful){
                Glide.with(this)
                    .load(task.result)
                    .into(imageViewFromRB)
            }else{

            }
        })
    }

}