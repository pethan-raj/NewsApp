package com.app.newsapp.api.service

import com.app.newsapp.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("articles")
    suspend fun getNewsResponse(
        @Query("format")format : String = "json",
        @Query("limit")limit : Int = 20,
        @Query("offset")offset : Int = 0
        ) : NewsResponse
}