package com.example.stylehelper.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.stylehelper.R
import com.example.stylehelper.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.shopTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_shopFragment)
        }

        binding.plusTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_plusFragment)
        }

        binding.alarmTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_alarmFragment)
        }

        binding.mypageTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_mypageFragment)
        }

        return binding.root
    }
}