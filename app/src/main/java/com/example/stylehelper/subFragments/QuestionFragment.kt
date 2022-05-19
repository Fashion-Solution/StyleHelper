package com.example.stylehelper.subFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.stylehelper.R
import com.example.stylehelper.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {

    private lateinit var binding : FragmentQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)

        binding.bestTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_questionFragment_to_bestFragment)
        }

        binding.dailyTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_questionFragment_to_dailyFragment)
        }

        binding.communityTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_questionFragment_to_communityFragment)
        }

        binding.mainTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_questionFragment_to_mainFragment)
        }
        return binding.root
    }
}