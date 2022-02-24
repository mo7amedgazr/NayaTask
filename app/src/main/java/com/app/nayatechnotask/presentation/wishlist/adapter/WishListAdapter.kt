package com.app.nayatechnotask.presentation.wishlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.nayatechnotask.databinding.WishlistItemLayoutBinding
import com.app.nayatechnotask.domain.entity.ListItem

class WishListAdapter :
    ListAdapter<ListItem, WishListAdapter.ItemViewHolder>(Companion) {

    interface OnItemTap {
        fun onSave(listItem: ListItem , position: Int)
    }

    fun setItemTapListener(tapListener: OnItemTap) {
        onTapListener = tapListener
    }

    private var currency: String? = null

    private var onTapListener: OnItemTap? = null
    private var context: Context? = null

    fun setCurrency(currency: String) {
        this.currency = currency
    }


    inner class ItemViewHolder(val binding: WishlistItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(
            oldItem: ListItem,
            newItem: ListItem
        ): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(
            oldItem: ListItem,
            newItem: ListItem
        ): Boolean =
            oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = WishlistItemLayoutBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.item = currentItem
        holder.binding.position = position
        holder.binding.currency = currency
        holder.binding.clickListener = onTapListener
        holder.binding.executePendingBindings()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }
}