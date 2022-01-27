package com.example.practice.module.history

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.bean.Data
import com.example.practice.databinding.FragmentHistoryBinding
import com.example.practice.module.history.graph.HistoryGraphActivity


class HistoryFragment : BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate) {

    private lateinit var historyViewModel: HistoryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    private fun initData() {
        historyViewModel =
            ViewModelProvider(this)[HistoryViewModel::class.java]
        getActivity()?.let { historyViewModel.getHistoryList() }
    }

    private fun initView() {
        val historyListView: RecyclerView = viewBinding.listView
        val graphView: ImageView = viewBinding.titleGraph.titleHistoryShowGraph
        graphView.setImageResource(R.mipmap.pie_chart)
        historyViewModel.historyListLiveData.observe(viewLifecycleOwner, Observer {
            var init: (View, Data) -> Unit = {v:View,d:Data ->
                var addressView = v.findViewById<TextView>(R.id.address)
                var dateview=v.findViewById<TextView>(R.id.time)
                var priceView=v.findViewById<TextView>(R.id.price)
                addressView.text = d.address
                dateview.text = d.date
                priceView.text = d.price
            }
            //get from networkApi
            var adapter = it.getOrNull()?.let { it1 ->
                HistoryListViewAdapter(R.layout.history_list_item,
                    it1.dataList,init)
            }
            historyListView.layoutManager= LinearLayoutManager(getActivity())
            historyListView.adapter=adapter

        })
        //グラフ画面へ遷移
        graphView.setOnClickListener{
            var intent = Intent(this.getActivity(), HistoryGraphActivity().javaClass)
            startActivity(intent)
        }
    }

}