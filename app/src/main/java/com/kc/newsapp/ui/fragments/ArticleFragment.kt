package com.kc.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kc.newsapp.R
import com.kc.newsapp.ui.NewsActivity
import com.kc.newsapp.ui.viewmodel.NewsViewModel

class ArticleFragment: Fragment(R.layout.fragment_article) {

    private lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }
    
}