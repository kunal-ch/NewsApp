package com.kc.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.kc.newsapp.R
import com.kc.newsapp.models.Article
import com.kc.newsapp.ui.NewsActivity
import com.kc.newsapp.ui.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment: Fragment(R.layout.fragment_article) {

    private lateinit var viewModel: NewsViewModel
    //private val args: ArticleFragmentArgs by navArgs<Article>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        val article = arguments?.getSerializable("article") as Article
        //val article = args.article
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }
    
}