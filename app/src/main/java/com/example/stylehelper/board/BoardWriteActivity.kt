package com.example.stylehelper.board

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.CompoundButton
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
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_board_write.*
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardWriteBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_write)

        binding.imgUploadBtn.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }

        binding.writeUploadBtn.setOnClickListener {
            val title = binding.titleArea.text.toString()
            val content = binding.contentArea.text.toString()
            val uid = FBAuth.getUid()
            val time = FBAuth.getTime()

            var style = mutableListOf<String>()

            if (binding.cb1.isChecked()) // 이 부분에 스타일 목록 적을 것
                style.add(binding.cb1.text.toString())
            if (binding.cb2.isChecked())
                style.add(binding.cb2.text.toString())
            if (binding.cb3.isChecked())
                style.add(binding.cb3.text.toString())

            //val tag = // 잠시 보류
            //val like = // 좋아요 기능 완성되면 추가하기
            //val viewCount = // 조회수 기능 => 그 게시글에 접속하면 +1 되도록함.

            //이미지 이름을 위해 키값부터 생성
            val key = FBRef.boardRef.push().key.toString()

            ImageUpload(key)

            FBRef.boardRef
                .child(key)
                .setValue(BoardModel(title, content, uid, time, style, likecount = 0))

            Toast.makeText(this, "게시글 입력 완료", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun ImageUpload(key :String){
            // Get the data from an ImageView as bytes
        try {
            var storage = Firebase.storage
            val storageRef = storage.reference
            val mountainsRef = storageRef.child(key+".png")
            val imageView = binding.imgArea

            imageView.isDrawingCacheEnabled = true
            imageView.buildDrawingCache()
            val bitmap = (imageView.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            var uploadTask = mountainsRef.putBytes(data)
            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
            }.addOnSuccessListener { taskSnapshot ->
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                // ...
            }
        } catch (e: NullPointerException) {
            Toast.makeText(this, "모든 부분을 작성해주셔야 합니다.", Toast.LENGTH_SHORT).show()

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 100){
            binding.imgArea.setImageURI(data?.data)
        }
    }
}