package com.example.practice.module.notifications

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.bean.NotificationData
import com.example.practice.databinding.FragmentNotificationsBinding
import com.example.practice.base.list.BaseRecycleViewAdapter.OnRecyclerItemClickListener
import com.example.practice.utils.LoadingDialogUtils


class NotificationsFragment :  BaseFragment<FragmentNotificationsBinding>(FragmentNotificationsBinding::inflate) {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var isExpend = false
    private var mLoadingDialog: Dialog? = null

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
        val titleView: TextView = viewBinding.titleLl.title
        var loadingDialog = LoadingDialogUtils()

        titleView.text = getString(R.string.title_notifications)
        notificationsViewModel.notificationsListLiveData.observe(viewLifecycleOwner, Observer {
            var init: (View, NotificationData) -> Unit = { v:View, d:NotificationData ->
                var titleTv = v.findViewById<TextView>(R.id.shop_title)
                var expandableTv=v.findViewById<TextView>(R.id.expandable_text)
                titleTv.text = d.title
                expandableTv.text = d.msg
            }

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

        notificationsViewModel.loadingLiveData.observe(viewLifecycleOwner,{
            if(it){
                mLoadingDialog = loadingDialog.createLoadingDialog(getActivity(),"Loading")
            } else {
                loadingDialog.closeDialog(mLoadingDialog)
            }
        })
    }
}