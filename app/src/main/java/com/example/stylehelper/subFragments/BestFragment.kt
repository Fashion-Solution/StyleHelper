package com.example.stylehelper.subFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

import com.example.stylehelper.R

import com.example.stylehelper.databinding.FragmentBestBinding
import com.example.stylehelper.subFragments.community.CommunityBoardModel
import com.example.stylehelper.subFragments.daily.DailyBoardModel
import com.example.stylehelper.utils.FBRef
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_best.*
import java.util.*

class BestFragment : Fragment() {

    private val boardList = mutableListOf<DailyBoardModel>()
    private val boardRankList = mutableListOf<DailyBoardModel>()
    private lateinit var databaseReference: DatabaseReference

    private lateinit var binding: FragmentBestBinding
    private var auth: FirebaseAuth? = null
    private val TAG = BestFragment::class.java.simpleName
    var i = true
    var j = true
    var k = true
    var q = true
    var a = 0
    var tmp = 100
    var first = 0
    var second = 0
    var third = 0
    var fourth = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        databaseReference = Firebase.database.reference


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_best, container, false)

        binding.mainTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bestFragment_to_mainFragment)
        }

        binding.dailyTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bestFragment_to_dailyFragment)
        }

        binding.communityTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bestFragment_to_communityFragment)
        }

        binding.questionTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bestFragment_to_questionFragment)
        }

        getBoardData()


        return binding.root
    }


    private fun getBoardData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val dataModel = dataSnapshot.getValue(DailyBoardModel::class.java)
                val post = dataSnapshot.getValue<DailyBoardModel>()
                for (dataModel in dataSnapshot.children) {
                    val item = dataModel.getValue((DailyBoardModel::class.java))
                    boardList.add(item!!)
                }
                rankBoardData()
                infoPosition()


            }


            override fun onCancelled(error: DatabaseError) {

            }
        }
        //FBRef에서 데이터를 가져온다.
        FBRef.dailyboardRef.addValueEventListener(postListener)

    }

    //likecount 랭크 먹이는 함수
    private fun rankBoardData() {
        boardList.sortByDescending { it.likecount }
        Log.d("here",boardList[0].title)

        Log.d("here", boardList.toString())

    }

    private fun infoPosition(){
        binding.firstTitle.setText(boardList[0].title)
        binding.firstScore.setText(boardList[0].likecount.toString())
        binding.secondTitle.setText(boardList[1].title)
        binding.secondScore.setText(boardList[1].likecount.toString())
        binding.thirdTitle.setText(boardList[2].title)
        binding.thirdScore.setText(boardList[2].likecount.toString())
        binding.fourthTitle.setText(boardList[3].title)
        binding.fourthScore.setText(boardList[3].likecount.toString())

    }
}