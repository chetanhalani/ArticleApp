package com.articleapp.model

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.articleapp.rest.ApiCallInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticleDataSource(private val apiCallInterface: ApiCallInterface) : PageKeyedDataSource<Int, Article>() {

    var isLoading : MutableLiveData<Boolean> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val records = apiCallInterface.getBlogs(1, params.requestedLoadSize)
            withContext(Dispatchers.Main) {
                isLoading.postValue(false)
                callback.onResult(
                    records,
                    null,
                    2
                )
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val records = apiCallInterface.getBlogs(params.key, params.requestedLoadSize)
            withContext(Dispatchers.Main) {
                isLoading.postValue(false)
                callback.onResult(
                    records,
                    params.key + 1
                )
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
    }

}