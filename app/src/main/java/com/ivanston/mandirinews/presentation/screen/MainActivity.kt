package com.ivanston.mandirinews.presentation.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import androidx.activity.viewModels
import com.ivanston.mandirinews.databinding.ActivityMainBinding
import com.ivanston.mandirinews.presentation.viewmodel.NewsViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModels()


    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getTopHeadlines().observe(this, Observer { articles ->
            val headlineArticle = articles.firstOrNull()
            if (headlineArticle != null) {
                // Bind headlineArticle to headline view
                binding.headlineTitleTextView.text = headlineArticle.title
                binding.headlineDescriptionTextView.text = headlineArticle.description
                binding.headlineImageView.load(headlineArticle.urlToImage) {
                    crossfade(true)
                }
            }

            binding.recyclerView.adapter = NewsAdapter(articles.drop(1))
        })

        viewModel.getAllNews("latest").observe(this, Observer { articles ->
            binding.recyclerView.adapter = NewsAdapter(articles)
        })
    }
}
