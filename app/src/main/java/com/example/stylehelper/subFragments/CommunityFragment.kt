package com.example.stylehelper.subFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.stylehelper.R
import com.example.stylehelper.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment() {

    private lateinit var binding : FragmentCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community, container, false)

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
}