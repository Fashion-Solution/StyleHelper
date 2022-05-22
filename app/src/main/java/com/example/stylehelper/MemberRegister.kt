package com.example.stylehelper

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MemberRegister : AppCompatActivity() {
    private var auth : FirebaseAuth? = null
    lateinit var btn_register: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContentView(R.layout.activity_member_register)
        btn_register = findViewById<Button>(R.id.btn_register)
        var edit_id : TextView = findViewById<TextView>(R.id.edit_id)
        var edit_pw : TextView = findViewById<TextView>(R.id.edit_pw)

        // 계정 생성 버튼
        btn_register.setOnClickListener {
            createAccount(edit_id.text.toString(),edit_pw.text.toString())
        }
    }

    // 계정 생성
    private fun createAccount(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this, "계정 생성 완료.",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish() // 가입창 종료
                    } else {
                        Toast.makeText(
                            this, "계정 생성 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}