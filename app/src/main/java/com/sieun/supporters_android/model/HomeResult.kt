package com.sieun.supporters_android.model

import com.google.gson.annotations.SerializedName

data class HomeResult(
    val home: Home
)

data class Home(
    val banners: List<Banner>,
    val categories: List<Category>
)

data class Banner(
    val id: Long,
    val title: String,
    val description: String,

    @SerializedName("image_url")
    val imageURL: String,

    @SerializedName("sponsor_item")
    val sponsorItem: SponsorItem
)

data class SponsorItem(
    val id: Long,

    @SerializedName("thumbnail_url")
    val thumbnailURL: String
)

data class Category(
    val id: Long,
    val name: String
)