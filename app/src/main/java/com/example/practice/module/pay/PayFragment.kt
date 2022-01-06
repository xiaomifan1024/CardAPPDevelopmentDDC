package com.example.practice.module.pay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentHomeBinding
import com.example.practice.databinding.FragmentNotificationsBinding
import com.example.practice.databinding.FragmentPayBinding
import com.example.practice.module.home.HomeViewModel
import com.example.practice.module.notifications.NotificationsViewModel

class PayFragment : BaseFragment<FragmentPayBinding>(FragmentPayBinding::inflate) {

    private lateinit var payViewModel: PayViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }
    private fun initData() {
        payViewModel =
            ViewModelProvider(this).get(PayViewModel::class.java)
    }

    private fun initView() {
        val textView: TextView = viewBinding.textPay
        payViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
    }


}