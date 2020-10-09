package com.articleapp.model

import java.text.SimpleDateFormat
import java.util.*

data class Article(
    var id: String,
    var createdAt: String,
    var content: String,
    var comments: Long,
    var likes: Long,
    var media: List<ArticleMedia>,
    var user: List<ArticleUser>,
) {
    fun getTimePassed(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
        val date = sdf.parse(createdAt)
        val curTime = Date()
        val diffInMins = (curTime.time - date.time) / 1000 / 60
        when (diffInMins) {
            0L -> {
                return "Just Now"
            }
            in 1..60 -> {
                // in minutes
                return " $diffInMins min"
            }
            in 61..1440 -> {
                // in hours
                return " ${diffInMins / 60} hr"
            }
            else -> {
                // in days
                return " ${diffInMins / 60 / 24} days"
            }

        }
        return ""
    }
}


data class ArticleMedia(
    var id: String,
    var blogId: String,
    var createdAt: String,
    var image: String,
    var title: String,
    var url: String,
)


data class ArticleUser(
    var id: String,
    var blogId: String,
    var createdAt: String,
    var name: String,
    var avatar: String,
    var lastname: String,
    var about: String,
    var designation: String,
    var city: String,
)