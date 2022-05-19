package com.example.stylehelper.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.stylehelper.MemberRegister
import com.example.stylehelper.R
import com.example.stylehelper.databinding.FragmentShopBinding
import kotlinx.android.synthetic.main.fragment_shop.*
import kotlinx.android.synthetic.main.fragment_shop.view.*

class ShopFragment : Fragment() {

    private lateinit var binding : FragmentShopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop, container, false)


        binding.shop1.setOnClickListener {
            val url = "https://m.unitropero.com/"
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse(url))
            startActivity(intent)
        }
        binding.shop2.setOnClickListener {
            val url = "https://www.google.com/"
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse(url))
            startActivity(intent)
        }

//프레그먼트--------------------------------------------------------------------------
        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_shopFragment_to_homeFragment)
        }

        binding.plusTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_shopFragment_to_plusFragment)
        }

        binding.alarmTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_shopFragment_to_alarmFragment)
        }

        binding.mypageTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_shopFragment_to_mypageFragment)
        }

        return binding.root
    }
}