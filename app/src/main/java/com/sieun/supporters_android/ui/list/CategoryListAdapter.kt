package com.sieun.supporters_android.ui.list

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sieun.supporters_android.R
import com.sieun.supporters_android.databinding.ItemInfiniteListBinding
import com.sieun.supporters_android.model.CategoryItem
import com.sieun.supporters_android.ui.DetailActivity
import com.sieun.supporters_android.ui.HomeActivity

class CategoryListAdapter(private val categoryName: String) :
    ListAdapter<CategoryItem, CategoryItemViewHolder>(CategoryItem.diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_infinite_list,
                parent,
                false
            ),
            categoryName
        )
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class CategoryItemViewHolder(val binding: ItemInfiniteListBinding, private val categoryName: String) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            Intent(binding.root.context, DetailActivity::class.java)
                .putExtra(HomeActivity.CATEGORY_NAME, categoryName)
                .putExtra(ITEM_ID, binding.categoryItem?.id ?: 0)
                .let {
                    binding.root.context.startActivity(it)
                }
        }
        binding.itemImageview.clipToOutline = true
    }

    fun bind(categoryItem: CategoryItem) {
        binding.categoryItem = categoryItem
        binding.itemContainerTags.removeAllViews()
        categoryItem.tags.split(",").forEach {
            TextView(binding.root.context).apply {
                setTextColor(resources.getColor(R.color.gray02))
                textSize = 10f
                background = resources.getDrawable(R.drawable.tv_bg_rectangle)
                text = "#$it"
                layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                    setMargins(8,4,8,4)
                    setPadding(12,0,12,0)
                }
            }.also {
                binding.itemContainerTags.addView(it)
            }
        }
    }

    companion object {
        const val ITEM_ID = "itemID"
    }
}
