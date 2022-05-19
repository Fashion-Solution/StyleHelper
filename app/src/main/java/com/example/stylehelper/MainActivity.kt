package com.example.stylehelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.stylehelper.board.BoardListActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var auth : FirebaseAuth? = null

    // -----구글 로그인 연동에 필요한 변수-----
    private var googleSignInClient : GoogleSignInClient? = null
    private var GOOGLE_LOGIN_CODE = 9001


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContentView(R.layout.activity_main)



        btn_register.setOnClickListener {   //회원가입 화면 이동
            var intent1 = Intent(applicationContext, MemberRegister::class.java)
            startActivity(intent1)
            }

        btn_login.setOnClickListener {      //로그인
                signIn(edit_id.text.toString(),edit_pw.text.toString())
        }

        btn_nonmember.setOnClickListener {   //비회원 로그인
            moveMainPage(auth?.currentUser)
        }

        //---구글 로그인 버튼과 기능---
        sign_in_button.setOnClickListener { googleLogin() }

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)


}



    private fun signIn(email: String, password: String) {   //로그인 함수

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext, "로그인에 성공 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        moveMainPage(auth?.currentUser)

                    } else {
                        Toast.makeText(
                            baseContext, "로그인에 실패 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }


    //-------------구글 로그인-------------

    fun googleLogin(){
        var signInIntent = googleSignInClient?.signInIntent
        startActivityForResult(signInIntent,GOOGLE_LOGIN_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_LOGIN_CODE){
            var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)!!
            // 구글API가 넘겨주는 값 받아옴

            if(result.isSuccess) {
                var accout = result.signInAccount
                firebaseAuthWithGoogle(accout)
                Toast.makeText(this,"성공",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"실패",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun firebaseAuthWithGoogle(account : GoogleSignInAccount?){
        var credential = GoogleAuthProvider.getCredential(account?.idToken,null)
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener{
                    task ->
                if(task.isSuccessful){
                    // 아이디, 비밀번호 맞을 때
                    moveMainPage(task.result?.user)
                }else{
                    // 틀렸을 때
                    Toast.makeText(this,task.exception?.message,Toast.LENGTH_SHORT).show()
                }
            }
    }

    //로그인 성공 시 페이지 이동
    fun moveMainPage(user: FirebaseUser?){
        if( user!= null){
            startActivity(Intent(this, LobbyActivity::class.java))
            finish()
        }
    }

}

