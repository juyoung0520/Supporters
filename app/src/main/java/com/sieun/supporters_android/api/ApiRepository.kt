package com.sieun.supporters_android.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val service: ApiService
) {
    suspend fun getHome() = withContext(Dispatchers.IO) { service.getHome() }
}