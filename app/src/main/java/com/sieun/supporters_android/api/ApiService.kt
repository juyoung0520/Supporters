package com.sieun.supporters_android.api

import com.sieun.supporters_android.model.HomeResult
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/home")
    suspend fun getHome(): Response<HomeResult>
}