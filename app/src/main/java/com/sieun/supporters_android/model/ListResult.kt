package com.sieun.supporters_android.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class CategoryItemsResult(
    val items: List<CategoryItem>
) {
    companion object {
        val empty = CategoryItemsResult(emptyList())
    }
}

data class CategoryItem(
    val id: Long,
    val title: String,
    @SerializedName("description")val desc: String,
    @SerializedName("period_id") val periodId: Int,
    @SerializedName("site_url") val siteUrl: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String
) {
    companion object {

        val diffUtil = object:DiffUtil.ItemCallback<CategoryItem>() {
            override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}
