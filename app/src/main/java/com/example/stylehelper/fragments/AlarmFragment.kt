package com.example.stylehelper.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.stylehelper.R
import com.example.stylehelper.databinding.FragmentAlarmBinding

class AlarmFragment : Fragment() {

    private lateinit var binding : FragmentAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alarm, container, false)

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_alarmFragment_to_homeFragment)
        }

        binding.shopTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_alarmFragment_to_shopFragment)
        }

        binding.plusTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_alarmFragment_to_plusFragment)
        }

        binding.mypageTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_alarmFragment_to_mypageFragment)
        }

        return binding.root
    }
}