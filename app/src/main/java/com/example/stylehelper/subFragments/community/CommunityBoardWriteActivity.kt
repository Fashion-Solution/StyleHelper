package com.example.stylehelper.subFragments.community

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.stylehelper.R
import com.example.stylehelper.databinding.ActivityCommunityBoardWriteBinding
import com.example.stylehelper.utils.FBAuth
import com.example.stylehelper.utils.FBRef
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class CommunityBoardWriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommunityBoardWriteBinding

    private var isImageUpload : Boolean = false

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_community_board_write)

        binding.imgUploadBtn.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
            isImageUpload = true
        }

        binding.writeUploadBtn.setOnClickListener {
            val title = binding.titleArea.text.toString()
            val content = binding.contentArea.text.toString()
            val uid = FBAuth.getUid()
            val time = FBAuth.getTime()
            var likes = mutableMapOf<String, Boolean>()
            var likecount = 0

            //val tag = // 잠시 보류
            //val like = // 좋아요 기능 완성되면 추가하기
            //val viewCount = // 조회수 기능 => 그 게시글에 접속하면 +1 되도록함.

            //이미지 이름을 위해 키값부터 생성
            val key = FBRef.communityboardRef.push().key.toString()

            if(isImageUpload) {
                ImageUpload(key)
            }

            FBRef.communityboardRef
                .child(key)
                .setValue(CommunityBoardModel(title, content, uid, time, likecount, likes))

            Toast.makeText(this, "소식통 입력 완료", Toast.LENGTH_LONG).show()

            finish()
        }
    }

    private fun ImageUpload(key :String){
        // Get the data from an ImageView as bytes

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


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 100){
            binding.imgArea.setImageURI(data?.data)
        }
    }

}