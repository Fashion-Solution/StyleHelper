package com.example.stylehelper.board

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class BoardModel (
    var title : String="",
    var content : String="",
    var uid : String="",
    var time : String="",
    var style : MutableList<String> = mutableListOf<String>(),
    var likecount : Int=0,
    var likes : MutableMap<String, Boolean> = mutableMapOf<String,Boolean>()
    //val tag : Array<String>=Array,
    //val imageURL : String="",
    //val like : Int=0,
    //val viewCount : Int=0,
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "title" to title,
            "content" to content,
            "uid" to uid,
            "time" to time,
            "style" to style,
            "likecount" to likecount,
            "likes" to likes
        )
    }
}