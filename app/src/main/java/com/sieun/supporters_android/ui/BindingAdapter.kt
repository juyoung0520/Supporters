package com.sieun.supporters_android.ui

import android.graphics.drawable.Drawable
import android.webkit.URLUtil
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


@BindingAdapter("items")
fun <T, VH : RecyclerView.ViewHolder> RecyclerView.setItems(items: List<T>?) {
    if (items == null) return
    (adapter as? ListAdapter<T, VH>)?.submitList(items)
}

@BindingAdapter("image", "placeholder", requireAll = false)
fun ImageView.setImage(url: String?, placeholder: Drawable? = null) {
    if (URLUtil.isHttpUrl(url) || URLUtil.isHttpsUrl(url)) {
        Glide.with(this)
            .load(url)
            .placeholder(placeholder)
            .into(this)
    } else setImageDrawable(placeholder)
}