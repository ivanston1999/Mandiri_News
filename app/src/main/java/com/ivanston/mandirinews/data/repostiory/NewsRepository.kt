package com.ivanston.mandirinews.data.repostiory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ivanston.mandirinews.data.network.api.model.Article
import com.ivanston.mandirinews.data.network.api.model.NewsResponse
import com.ivanston.mandirinews.data.network.api.service.NewsApiClient
import com.ivanston.mandirinews.data.network.api.service.NewsApiService
import com.ivanston.mandirinews.utils.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {
    private val newsApiService = NewsApiClient.retrofit.create(NewsApiService::class.java)
    private val apiKey = API_KEY

    fun getTopHeadlines(country: String): LiveData<List<Article>> {
        val data = MutableLiveData<List<Article>>()

        newsApiService.getTopHeadlines(country, apiKey).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    data.value = response.body()?.articles ?: emptyList()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                data.value = emptyList()
            }
        })

        return data
    }

    fun getAllNews(query: String): LiveData<List<Article>> {
        val data = MutableLiveData<List<Article>>()

        newsApiService.getAllNews(query, apiKey).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    data.value = response.body()?.articles ?: emptyList()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                data.value = emptyList()
            }
        })

        return data
    }
}
