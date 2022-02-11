package com.example.practice.module.notifications

import android.view.View
import com.example.practice.base.list.BaseRecycleViewAdapter
import com.example.practice.bean.Data
import com.example.practice.bean.NotificationData

class NotificationsListViewAdapter(layoutResourceId: Int, items: List<NotificationData>, init: (View, NotificationData) -> Unit) :
    BaseRecycleViewAdapter<NotificationData>(layoutResourceId, items, init){

}