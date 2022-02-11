package com.example.practice.module.setting

import android.view.View
import com.example.practice.base.list.BaseRecycleViewAdapter
import com.example.practice.data.SettingsData

class SettingsListViewAdapter (layoutResourceId: Int, items: List<SettingsData>, init: (View, SettingsData) -> Unit) :
    BaseRecycleViewAdapter<SettingsData>(layoutResourceId, items, init){


}