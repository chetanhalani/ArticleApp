package com.articleapp.rest

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.articleapp.model.Article
import com.google.gson.Gson
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET
import retrofit2.http.Query


interface ApiCallInterface {

    @GET(ApiUrls.Blogs)
    suspend fun getBlogs(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): ArrayList<Article>

    companion object {

        fun getApiInstance() : ApiCallInterface {
            val retrofit : Retrofit = Retrofit.Builder()
                .baseUrl(ApiUrls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
            return retrofit.create(ApiCallInterface::class.java)
        }

    }
}