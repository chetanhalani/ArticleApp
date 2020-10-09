package com.articleapp

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.articleapp.adapter.ArticleAdapter
import com.articleapp.model.ArticleViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ArticleViewModel
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        initAdapter()
    }

    private fun initAdapter() {
        articleAdapter = ArticleAdapter()
        rv_articles.adapter = articleAdapter
        viewModel.articleList.observe(this,
            {
                articleAdapter.submitList(it)
            })

        viewModel.getLoading().observe(this, {
            if(it) {
                progress_bar.visibility = VISIBLE
            } else {
                progress_bar.visibility = GONE
            }
        })
    }

}