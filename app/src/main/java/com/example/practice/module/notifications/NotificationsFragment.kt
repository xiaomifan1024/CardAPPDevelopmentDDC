package com.example.practice.module.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentHomeBinding
import com.example.practice.databinding.FragmentNotificationsBinding

class NotificationsFragment :  BaseFragment<FragmentNotificationsBinding>(FragmentNotificationsBinding::inflate) {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }
    private fun initData() {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)
    }

    private fun initView() {
        val textView: TextView = viewBinding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
    }
}