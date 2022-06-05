package com.example.stylehelper.subFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.stylehelper.R
import com.example.stylehelper.databinding.FragmentCommunityBinding
import com.example.stylehelper.subFragments.community.CommunityBoardInsideActivity
import com.example.stylehelper.subFragments.community.CommunityBoardModel
import com.example.stylehelper.subFragments.community.CommunityBoardWriteActivity
import com.example.stylehelper.subFragments.community.CommunityListViewAdapter
import com.example.stylehelper.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class CommunityFragment : Fragment() {

    lateinit var LVAdapter : CommunityListViewAdapter
    private val boardDatalist = mutableListOf<CommunityBoardModel>()
    private val boardKeyList = mutableListOf<String>()

    private lateinit var binding : FragmentCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community, container, false)

        binding.writeBtn.setOnClickListener{
            val intent = Intent(context, CommunityBoardWriteActivity::class.java)
            //intent.putExtra("key", boardKeyList[position])
            startActivity(intent)
        }

        //리스트 뷰 생성
        LVAdapter = CommunityListViewAdapter(boardDatalist)

        var lv = binding.lv

        lv.adapter = LVAdapter

        lv.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(activity, CommunityBoardInsideActivity::class.java)
            intent.putExtra("key", boardKeyList[position])
            startActivity(intent)
        }

        getBoardData()

        binding.mainTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_communityFragment_to_mainFragment)
        }

        binding.dailyTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_communityFragment_to_dailyFragment)
        }

        binding.bestTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_communityFragment_to_bestFragment)
        }

        binding.questionTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_communityFragment_to_questionFragment)
        }
        return binding.root
    }

    private fun getBoardData(){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                //리스트 새로고침
                boardDatalist.clear()

                for(dataModel in dataSnapshot.children){
                    val item = dataModel.getValue(CommunityBoardModel::class.java)
                    boardDatalist.add(item!!)
                    boardKeyList.add(dataModel.key.toString())
                }
                boardDatalist.reverse()
                boardKeyList.reverse()
                //리스트 재동기화
                LVAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        //FBRef에서 데이터를 가져온다.
        FBRef.communityboardRef.addValueEventListener(postListener)
    }
}