package com.articleapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class ArticleViewModel : ViewModel() {

    var articleList: LiveData<PagedList<Article>>
    private val pageSize = 10
    private val articleDataFactory: ArticleDataFactory = ArticleDataFactory()

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        articleList = LivePagedListBuilder(articleDataFactory, config).build()
    }



    fun getLoading(): LiveData<Boolean> = Transformations.switchMap(
        articleDataFactory.articleDataSourceLiveData,
        ArticleDataSource::isLoading
    )

}