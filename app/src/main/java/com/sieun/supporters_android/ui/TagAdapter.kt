package com.sieun.supporters_android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sieun.supporters_android.databinding.ItemTagListBinding

class TagAdapter: ListAdapter<String, TagAdapter.ViewHolder>(callback) {
    companion object {
        private val callback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem
        }
    }

    class ViewHolder(
        private val binding: ItemTagListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder = ItemTagListBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
                .let(::ViewHolder)
        }

        fun bind(item: String) = with(binding) {
            this.tag = tag
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))
}