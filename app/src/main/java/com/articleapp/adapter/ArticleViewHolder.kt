package com.articleapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.articleapp.R
import com.articleapp.model.Article
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.article_row_view.view.*
import java.math.RoundingMode
import java.text.DecimalFormat


class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(article: Article) {
        if(article.user.isNotEmpty()) {
            itemView.tv_user_name.text = article.user[0].name
            itemView.tv_user_designation.text = article.user[0].designation
            Glide.with(itemView.iv_user.context).load(article.user[0].avatar).circleCrop().into(itemView.iv_user)
        }
        if(article.media.isNotEmpty()) {
            Glide.with(itemView.iv_blog.context).load(article.media[0].image).into(itemView.iv_blog)
            itemView.iv_blog.visibility = VISIBLE
        } else {
            itemView.iv_blog.visibility = GONE
        }
        itemView.tv_description.text = article.content
        itemView.tv_likes.text = getKCount(article.likes, "Likes")
        itemView.tv_comments.text = getKCount(article.comments, "Comments")
        itemView.tv_time.text = article.getTimePassed()
    }

    fun getKCount(count: Long, append: String) : String {
        return if(count > 1000) {
            val rem = count%1000F/1000F
            val decimalFormat = DecimalFormat("#.#")
            decimalFormat.roundingMode = RoundingMode.CEILING
            "${decimalFormat.format((count/1000) + rem)}K $append"
        } else {
            "$count $append"
        }
    }

    companion object {
        fun createView(group: ViewGroup): ArticleViewHolder {
            val view = LayoutInflater.from(group.context)
                .inflate(R.layout.article_row_view, group, false)
            return ArticleViewHolder(view)
        }
    }
}