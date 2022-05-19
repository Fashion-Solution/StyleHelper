package com.example.stylehelper.board

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil

import com.example.stylehelper.R
import com.example.stylehelper.databinding.ActivityBoardWriteBinding
import com.example.stylehelper.databinding.ActivityIntroBinding
import com.example.stylehelper.utils.FBAuth
import com.example.stylehelper.utils.FBRef
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardWriteBinding



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_write)



        binding.writeUploadBtn.setOnClickListener {
            val title = binding.titleArea.text.toString()
            val content = binding.contentArea.text.toString()
            val uid = FBAuth.getUid()
            val time = FBAuth.getTime()


            FBRef.boardRef
                .push()
                .setValue(BoardModel(title, content, uid, time))


            Toast.makeText(this,"게시글 입력 완료",Toast.LENGTH_LONG).show()
            finish()
            /*val writeBtn = findViewById<Button>(R.id.writeUploadBtn)
        writeBtn.setOnClickListener {

            val writetext = findViewById<EditText>(R.id.writeTextArea)
            val database = Firebase.database
            val myRef = database.getReference("board")

            myRef.push().setValue(
                BoardModel(writetext.text.toString())
            )*/


        }
    }
}