package com.example.practice.module.notifications

import android.view.View
import android.widget.Filter
import android.widget.Filterable
import com.example.practice.base.list.BaseRecycleViewAdapter
import com.example.practice.bean.Data
import com.example.practice.bean.NotificationData
import java.util.ArrayList




class NotificationsListViewAdapter(layoutResourceId: Int, items: List<NotificationData>, init: (View, NotificationData) -> Unit) :
    BaseRecycleViewAdapter<NotificationData>(layoutResourceId, items, init), Filterable {
    private var mSourceList: List<NotificationData> = items
    private var mFilterList: List<NotificationData> = items

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults? {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    mFilterList = mSourceList
                } else {
                    val filteredList: MutableList<NotificationData> = ArrayList()
                    for (str in mSourceList) {
                        if (str.title.contains(charString)) {
                            filteredList.add(str)
                        }
                    }
                    mFilterList = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = mFilterList
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: FilterResults
            ) {
                mFilterList = filterResults.values as List<NotificationData>
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return mFilterList.size
    }

}