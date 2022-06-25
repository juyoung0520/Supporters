package com.sieun.supporters_android.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sieun.supporters_android.R
import com.sieun.supporters_android.databinding.ItemInfiniteListBinding
import com.sieun.supporters_android.model.CategoryItem

class CategoryListAdapter :
    ListAdapter<CategoryItem, CategoryItemViewHolder>(CategoryItem.diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_infinite_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.binding.categoryItem = getItem(position)
    }

}

class CategoryItemViewHolder(val binding: ItemInfiniteListBinding) :
    RecyclerView.ViewHolder(binding.root)
