package com.articleapp.rest

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiUrls {
    const val BASE_URL = "https://5e99a9b1bc561b0016af3540.mockapi.io/jet2/api/v1/"
    const val Blogs = "blogs"
}