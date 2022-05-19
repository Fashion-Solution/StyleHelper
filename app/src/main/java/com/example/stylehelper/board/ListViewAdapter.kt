package com.example.stylehelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.stylehelper.board.BoardModel

class ListViewAdapter(val List : MutableList<BoardModel>) :BaseAdapter() {
    override fun getCount(): Int {
        return List.count()
    }

    override fun getItem(position: Int): Any {
        return List[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView

        if (view == null) {
            view =
                LayoutInflater.from(parent?.context).inflate(R.layout.listview_item, parent, false)

        }

        val title = view?.findViewById<TextView>(R.id.title)
        val time = view?.findViewById<TextView>(R.id.time)
        title!!.text = List[position].title
        time!!.text = List[position].time
        return view!!
    }


}