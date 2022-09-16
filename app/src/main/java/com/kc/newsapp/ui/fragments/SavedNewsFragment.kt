package com.kc.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kc.newsapp.R
import com.kc.newsapp.adapters.NewsAdapter
import com.kc.newsapp.listener.NewsItemClickListener
import com.kc.newsapp.models.Article
import com.kc.newsapp.ui.NewsActivity
import com.kc.newsapp.ui.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_saved_news.*

class SavedNewsFragment: Fragment(R.layout.fragment_saved_news) {

    private lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        setupRecyclerView()

        val itemClickListener = object : NewsItemClickListener {
            override fun onItemClicked(article: Article) {
                val bundle = Bundle().apply {
                    putSerializable("article", article)
                }
                findNavController().navigate(R.id.action_searchNewsFragment_to_articleFragment, bundle)
            }
        }

        newsAdapter.setOnClickListener(itemClickListener)
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}