package com.sieun.supporters_android.ui.list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sieun.supporters_android.model.CategoryItemsResult

@BindingAdapter("bind_categorylist")
fun bindCategoryList(recyclerView: RecyclerView, list: CategoryItemsResult) {
    (recyclerView.adapter as CategoryListAdapter).submitList(list.items)
}