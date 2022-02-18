package com.example.practice.module.setting

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.base.list.BaseRecycleViewAdapter
import com.example.practice.bean.NotificationData
import com.example.practice.data.SettingsData
import com.example.practice.databinding.FragmentPayBinding
import com.example.practice.databinding.FragmentSettingsBinding
import com.example.practice.module.pay.PayViewModel
import com.example.practice.module.setting.cardlogin.CardLoginActivity

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private lateinit var settingViewModel: SettingsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }
    private fun initData() {
        settingViewModel =
            ViewModelProvider(this)[SettingsViewModel::class.java]

    }

    private fun initView() {
        val settingListView = viewBinding.settingListView
        val titleView: TextView = viewBinding.titleLl.title
        var settingsDataList = settingViewModel.getSettingsData()
        var init: (View, SettingsData) -> Unit = { v: View, d: SettingsData ->
            var imgView =  v.findViewById<ImageView>(R.id.setting_image_view)
            var textView = v.findViewById<TextView>(R.id.setting_text_view)
            imgView.setImageResource(d.imageViewId)
            textView.text = d.text
        }
        var adapter = SettingsListViewAdapter(R.layout.setting_list_item,settingsDataList,init)
        settingListView.layoutManager = LinearLayoutManager(getActivity())
        settingListView.adapter = adapter
        titleView.text = "設定"
        adapter.setRecyclerItemClickListener(object :
            BaseRecycleViewAdapter.OnRecyclerItemClickListener {
                override fun onRecyclerItemClick(view:View,Position: Int) {
                    if(Position == 0) {
                        //カード登録画面へ遷移
                        val intent = Intent(getActivity(),CardLoginActivity().javaClass)
                        startActivity(intent)
                    }
                }
            })
    }


}