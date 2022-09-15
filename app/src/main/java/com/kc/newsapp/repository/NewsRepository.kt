package com.kc.newsapp.repository

import com.kc.newsapp.api.RetrofitInstance
import com.kc.newsapp.db.ArticleDatabase

class NewsRepository (
    val db : ArticleDatabase
        ) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
}