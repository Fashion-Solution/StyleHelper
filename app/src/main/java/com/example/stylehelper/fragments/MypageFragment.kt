package com.example.stylehelper.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stylehelper.R
import com.example.stylehelper.databinding.FragmentMypageBinding
import com.example.stylehelper.fragments.mypage_question.QuestionViewAdapter

class MypageFragment : Fragment() {

    private lateinit var binding : FragmentMypageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage, container, false)

        val items = ArrayList<String>()
        items.add("a")
        items.add("b")
        items.add("c")

        val rvAdapter = QuestionViewAdapter(items)

        binding.listView.adapter = rvAdapter

        binding.listView.layoutManager = LinearLayoutManager(requireContext())

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_mypageFragment_to_homeFragment)
        }

        binding.shopTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_mypageFragment_to_shopFragment)
        }

        binding.plusTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_mypageFragment_to_plusFragment)
        }

        binding.alarmTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_mypageFragment_to_alarmFragment)
        }

        return binding.root
    }
}