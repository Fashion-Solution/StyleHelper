package com.example.stylehelper.subFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.stylehelper.R
import com.example.stylehelper.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        binding.bestTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_bestFragment)
        }

        binding.dailyTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_dailyFragment)
        }

        binding.communityTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_communityFragment)
        }

        binding.questionTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_questionFragment)
        }
        return binding.root
    }
}