package com.example.stylehelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stylehelper.R

class AlarmViewAdapter(val items : ArrayList<String>) : RecyclerView.Adapter<AlarmViewAdapter.Viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewAdapter.Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.alarm_recycle, parent, false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: AlarmViewAdapter.Viewholder, position: Int) {
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