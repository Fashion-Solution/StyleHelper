package com.example.stylehelper.subFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.stylehelper.R
import com.example.stylehelper.databinding.FragmentBestBinding
import kotlinx.android.synthetic.main.fragment_best.*

class BestFragment : Fragment() {

    private lateinit var binding : FragmentBestBinding
    var i = true
    var j = true
    var k = true
    var q = true
    var first = 0
    var second = 0
    var third = 0
    var fourth = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        //좋아요 구현 ( DB 연동 X )
        binding.like1.setOnClickListener{
            if (i == true){
                like1.setImageResource(R.drawable.like)
                first++
                first_score.setText(first.toString())
                i = false
            }else{
                like1.setImageResource(R.drawable.like2)
                first--
                first_score.setText(first.toString())
                i = true
            }
        }
        binding.like2.setOnClickListener{
            if (j == true){
                like2.setImageResource(R.drawable.like)
                second++
                second_score.setText(second.toString())
                j = false
            }else{
                like2.setImageResource(R.drawable.like2)
                second--
                second_score.setText(second.toString())
                j = true
            }
        }
        binding.like3.setOnClickListener{
            if (k == true){
                like3.setImageResource(R.drawable.like)
                third++
                third_score.setText(third.toString())
                k = false
            }else{
                like3.setImageResource(R.drawable.like2)
                third--
                third_score.setText(third.toString())
                k = true
            }
        }
        binding.like4.setOnClickListener{
            if (q == true){
                like4.setImageResource(R.drawable.like)
                fourth++
                fourth_score.setText(fourth.toString())
                q = false
            }else{
                like4.setImageResource(R.drawable.like2)
                fourth--
                fourth_score.setText(fourth.toString())
                q = true
            }
        }
        return binding.root
    }
}