package com.example.practice.module.history

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentHistoryBinding

class HistoryFragment : BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate) {

    private lateinit var historyViewModel: HistoryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    private fun initData() {
        historyViewModel =
            ViewModelProvider(this).get(HistoryViewModel::class.java)
    }

    private fun initView() {
        val textView: TextView = viewBinding.textHistory
        historyViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
    }

}