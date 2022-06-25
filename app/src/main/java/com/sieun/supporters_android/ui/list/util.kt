package com.sieun.supporters_android.ui.list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sieun.supporters_android.model.CategoryItemsResult

@BindingAdapter("bind_categorylist")
fun bindCategoryList(recyclerView: RecyclerView, list: CategoryItemsResult) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = CategoryListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }
    (recyclerView.adapter as CategoryListAdapter).submitList(list.items)
}