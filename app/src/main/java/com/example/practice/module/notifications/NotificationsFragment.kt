package com.example.practice.module.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.bean.Data
import com.example.practice.bean.NotificationData
import com.example.practice.databinding.FragmentHomeBinding
import com.example.practice.databinding.FragmentNotificationsBinding
import com.example.practice.module.history.HistoryListViewAdapter
import com.ms.square.android.expandabletextview.ExpandableTextView
import com.example.practice.base.list.BaseRecycleViewAdapter.OnRecyclerItemClickListener




class NotificationsFragment :  BaseFragment<FragmentNotificationsBinding>(FragmentNotificationsBinding::inflate) {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var isExpend = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }
    private fun initData() {
        notificationsViewModel =
            ViewModelProvider(this)[NotificationsViewModel::class.java]
        getActivity().let {notificationsViewModel.getNotificationsList()}
        }

    private fun initView() {
        val notificationsListView: RecyclerView = viewBinding.nListView
        notificationsViewModel.notificationsListLiveData.observe(viewLifecycleOwner, Observer {
            var init: (View, NotificationData) -> Unit = { v:View, d:NotificationData ->
                var titleTv = v.findViewById<TextView>(R.id.shop_title)
                var expandableTv=v.findViewById<TextView>(R.id.expandable_text)
                titleTv.text = d.title
                expandableTv.text = d.msg
            }

            //get from networkapi
            var adapter = it.getOrNull()?.let { it1 ->
                NotificationsListViewAdapter(R.layout.notification_item,
                    it1.notificationList,init)
            }
            adapter?.setRecyclerItemClickListener(object : OnRecyclerItemClickListener {
                override fun onRecyclerItemClick(view:View,Position: Int) {
                    var tv =view.findViewById<TextView>(R.id.expandable_text)
                    isExpend = if (!isExpend) {
                        tv.minLines = 0
                        tv.maxLines = Integer.MAX_VALUE
                        true
                    } else {
                        tv.setLines(2)
                        false
                    }

                }
            })
            notificationsListView.layoutManager= LinearLayoutManager(getActivity())
            notificationsListView.adapter=adapter
        })
    }
}