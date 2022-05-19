package com.example.stylehelper.fragments.mypage_question

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stylehelper.R

class QuestionViewAdapter(val items : ArrayList<String>) : RecyclerView.Adapter<QuestionViewAdapter.Viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewAdapter.Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_ex, parent, false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: QuestionViewAdapter.Viewholder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(item : String) {

        }
    }

}