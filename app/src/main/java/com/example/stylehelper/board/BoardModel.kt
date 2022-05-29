package com.example.stylehelper.board

data class BoardModel (
    val title : String="",
    val content : String="",
    val uid : String="",
    val time : String="",
    val style : MutableList<String> = mutableListOf<String>(),
    val likecount : Int=0,
    val likes : MutableMap<String, Boolean> = mutableMapOf<String,Boolean>()
    //val tag : Array<String>=Array,
    //val imageURL : String="",
    //val like : Int=0,
    //val viewCount : Int=0,
)