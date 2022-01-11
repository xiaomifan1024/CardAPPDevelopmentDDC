package com.example.practice.base.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecycleViewAdapter<T> constructor(private val layoutId: Int, private val itemList: List<T>,
                                                     private val bindHolder: View.(T) -> Unit)
    : RecyclerView.Adapter<BaseRecycleViewAdapter.BaseViewHolder>() {

    private var itemClick: T.() -> Unit = {}
    constructor(layoutId: Int,
                itemList: List<T>,
                bindHolder: View.(T) -> Unit,
                itemClick: T.() -> Unit = {}) : this(layoutId, itemList, bindHolder) {
        this.itemClick = itemClick
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
            .inflate(layoutId, null)
        return BaseViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = itemList[holder?.adapterPosition!!]
        holder.itemView.bindHolder(item)
        holder.itemView.setOnClickListener {
            itemOnClick(it, position)
        }
    }
    override fun getItemCount() = itemList.size

    protected open fun itemOnClick(itemView: View, position: Int) {
        itemList[position].itemClick()
    }

    open class BaseViewHolder : RecyclerView.ViewHolder {
        open var mView: View? = null

        constructor(view: View) : super(view) {
            mView = view
        }

    }

}
