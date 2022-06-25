package com.sieun.supporters_android.model

import com.google.gson.annotations.SerializedName

data class BannerResult(
    val banner: CategoryItem,
    @SerializedName("banners")
    val items: List<CategoryItem>
)