package com.articleapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.articleapp.rest.ApiCallInterface

class ArticleDataFactory() : DataSource.Factory<Int, Article>() {

    val articleDataSourceLiveData = MutableLiveData<ArticleDataSource>()

    override fun create(): DataSource<Int, Article> {
        val articleDataSource = ArticleDataSource(ApiCallInterface.getApiInstance())
        articleDataSourceLiveData.postValue(articleDataSource)
        return articleDataSource
    }
}