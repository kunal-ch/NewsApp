package com.kc.newsapp.listener

import com.kc.newsapp.models.Article

interface NewsItemClickListener {
    fun onItemClicked(article: Article)
}