package com.kc.newsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.kc.newsapp.repository.NewsRepository

class NewsViewModel(
    val newsRepository: NewsRepository
)
    : ViewModel() {
}