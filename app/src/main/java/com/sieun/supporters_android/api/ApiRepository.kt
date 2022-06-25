package com.sieun.supporters_android.api

import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val service: ApiService
) {
    suspend fun fetchCategoryItem(categoryNum: Int) = service.fetchCategoryItem(categoryNum)

}