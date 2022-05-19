package com.example.stylehelper.fragments.mypage_question

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stylehelper.R

class QuestionListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_mypage)

        val rv : RecyclerView = findViewById(R.id.listView)

        val items = ArrayList<String>()
        items.add("a")
        items.add("b")
        items.add("c")

        val rvAdapter = QuestionViewAdapter(items)
        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(this)

    }
}