package com.sieun.supporters_android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sieun.supporters_android.databinding.ItemBannerBinding
import com.sieun.supporters_android.model.Banner


class BannerAdapter(
    private val onClickItem: (Banner) -> Unit,
    private val provider: ViewOutlineProvider,
) : ListAdapter<Banner, BannerAdapter.ViewHolder>(callback) {
    companion object {
        private val callback = object : DiffUtil.ItemCallback<Banner>() {
            override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean =
                oldItem == newItem
        }
    }

    class ViewHolder(
        private val binding: ItemBannerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder = ItemBannerBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
                .let(::ViewHolder)
        }

        fun bind(
            item: Banner,
            onClickItem: (Banner) -> Unit,
            provider: ViewOutlineProvider
        ) = with(binding) {
            ivBanner.outlineProvider = provider
            ivBanner.clipToOutline = true
            this.item = item
            ivBanner.setOnClickListener { onClickItem(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position), onClickItem, provider)
}