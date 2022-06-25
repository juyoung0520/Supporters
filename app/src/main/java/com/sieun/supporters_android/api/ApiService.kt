package com.sieun.supporters_android.api

import com.sieun.supporters_android.model.CategoryItemsResult
import com.sieun.supporters_android.model.HomeResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/home")
    suspend fun getHome(): Response<HomeResult>

    @GET("/sponsors/categories/{categoryNum}/items")
    suspend fun fetchCategoryItem(@Path("categoryNum") categoryNum: Long): Response<CategoryItemsResult>
}