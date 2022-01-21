package com.example.practice.module.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentHomeBinding
import com.example.practice.module.PushTestActivity

class HomeFragment :  BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private lateinit var homeViewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    private fun initData() {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
//        homeViewModel.requestHomeInfo()
    }

    private fun initView() {
        val textView: TextView = viewBinding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        homeViewModel.homeInfoLiveData.observe(viewLifecycleOwner, Observer {
            textView.text = it.getOrNull()?.title
        })

    }

}