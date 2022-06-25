package com.sieun.supporters_android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sieun.supporters_android.databinding.ItemCategoryBinding
import com.sieun.supporters_android.model.Category

class CategoryAdapter(
    private val onClickItem: (Category) -> Unit,
    private val categoryWidth: Int
) : ListAdapter<Category, CategoryAdapter.ViewHolder>(callback) {
    companion object {
        private val callback = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
                oldItem == newItem
        }
    }

    class ViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder = ItemCategoryBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
                .let(::ViewHolder)
        }

        fun bind(
            item: Category,
            onClickItem: (Category) -> Unit,
            width: Int
        ) = with(binding) {
            ivCategory.clipToOutline = true
            ivCategory.layoutParams = ViewGroup.LayoutParams(width, width)
            this.item = item
            root.setOnClickListener { onClickItem(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position), onClickItem, categoryWidth)
}