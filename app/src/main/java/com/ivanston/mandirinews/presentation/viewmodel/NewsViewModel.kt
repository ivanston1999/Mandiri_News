package com.ivanston.mandirinews.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ivanston.mandirinews.data.network.api.model.Article
import com.ivanston.mandirinews.data.repostiory.NewsRepository

class NewsViewModel : ViewModel() {
    private val repository = NewsRepository()

    fun getTopHeadlines(): LiveData<List<Article>> {
        return repository.getTopHeadlines("us")
    }

    fun getAllNews(query: String): LiveData<List<Article>> {
        return repository.getAllNews(query)
    }
}
