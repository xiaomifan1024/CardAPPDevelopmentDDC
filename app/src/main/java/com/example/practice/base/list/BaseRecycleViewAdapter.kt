package com.example.practice.base.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecycleViewAdapter<T>(val layoutResourceId: Int, val items: List<T>, val init: (View, T) -> Unit) :
    RecyclerView.Adapter<BaseRecycleViewAdapter.BaseViewHolder<T>>() {

    private var itemClick: T.() -> Unit = {}
    constructor(layoutId: Int,
                itemList: List<T>,
                bindHolder: View.(T) -> Unit,
                itemClick: T.() -> Unit = {}) : this(layoutId, itemList, bindHolder) {
        this.itemClick = itemClick
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecycleViewAdapter.BaseViewHolder<T> {
        val itemView = LayoutInflater.from(parent?.context).inflate(layoutResourceId, parent, false)
        return BaseViewHolder(itemView, init)
    }

    override fun onBindViewHolder(holder: BaseRecycleViewAdapter.BaseViewHolder<T>, position: Int) {
        holder.bindHolder(items[position])
        holder.itemView.setOnClickListener {
            itemOnClick(it, position)
        }
    }

    override fun getItemCount() = items.size


    protected open fun itemOnClick(itemView: View, position: Int) {
        items[position].itemClick()
    }

    class BaseViewHolder<T>(view: View, val init: (View, T) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindHolder(item: T) {
            init(itemView, item)
        }
    }

}
