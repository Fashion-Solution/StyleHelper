package com.example.stylehelper.subFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.stylehelper.R
import com.example.stylehelper.databinding.FragmentDailyBinding
import com.example.stylehelper.subFragments.daily.DailyBoardInsideActivity
import com.example.stylehelper.subFragments.daily.DailyBoardModel
import com.example.stylehelper.subFragments.daily.DailyBoardWriteActivity
import com.example.stylehelper.subFragments.daily.DailyListViewAdapter
import com.example.stylehelper.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class DailyFragment : Fragment() {

    lateinit var LVAdapter : DailyListViewAdapter
    private val boardDatalist = mutableListOf<DailyBoardModel>()
    private val boardKeyList = mutableListOf<String>()

    private lateinit var binding : FragmentDailyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_daily, container, false)

        binding.writeBtn.setOnClickListener{
            val intent = Intent(context, DailyBoardWriteActivity::class.java)
            //intent.putExtra("key", boardKeyList[position])
            startActivity(intent)
        }

        //리스트 뷰 생성
        LVAdapter = DailyListViewAdapter(boardDatalist)

        var lv = binding.lv

        lv.adapter = LVAdapter

        lv.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(activity, DailyBoardInsideActivity::class.java)
            intent.putExtra("key", boardKeyList[position])
            startActivity(intent)
        }

        getBoardData()

        // 프래그먼트 이동
        binding.mainTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_dailyFragment_to_mainFragment)
        }

        binding.bestTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_dailyFragment_to_bestFragment)
        }

        binding.communityTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_dailyFragment_to_communityFragment)
        }

        binding.questionTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_dailyFragment_to_questionFragment)
        }
        return binding.root
    }

    private fun getBoardData(){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                //리스트 새로고침
                boardDatalist.clear()

                for(dataModel in dataSnapshot.children){
                    val item = dataModel.getValue(DailyBoardModel::class.java)
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
        FBRef.dailyboardRef.addValueEventListener(postListener)
    }
}