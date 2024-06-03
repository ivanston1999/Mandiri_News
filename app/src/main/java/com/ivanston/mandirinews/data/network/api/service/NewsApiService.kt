package com.ivanston.mandirinews.data.network.api.service

import com.ivanston.mandirinews.data.network.api.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApiService {
    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
    ): Call<NewsResponse>

    @GET("everything")
    fun getAllNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String,
    ): Call<NewsResponse>
}
