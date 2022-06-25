package com.sieun.supporters_android.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val service: ApiService
) {
    suspend fun getHome() = withContext(Dispatchers.IO) { service.getHome() }

    suspend fun fetchCategoryItem(categoryNum: Long) = service.fetchCategoryItem(categoryNum)

    suspend fun getBanner(id: Long) = withContext(Dispatchers.IO) { service.getBanner(id) }

    suspend fun getDetailItem(id: Long) = withContext(Dispatchers.IO) { service.getDetailItem(id) }
}