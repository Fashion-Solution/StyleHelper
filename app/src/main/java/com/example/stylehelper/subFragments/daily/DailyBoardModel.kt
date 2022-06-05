package com.example.stylehelper.subFragments.daily

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class DailyBoardModel (
    var title : String="",
    var content : String="",
    var uid : String="",
    var time : String="",
    var style : MutableList<String> = mutableListOf<String>(),
    var likecount : Int=0,
    var likes : MutableMap<String, Boolean> = HashMap()
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