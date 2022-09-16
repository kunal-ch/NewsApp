package com.kc.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kc.newsapp.R
import com.kc.newsapp.adapters.NewsAdapter
import com.kc.newsapp.db.ArticleDatabase
import com.kc.newsapp.listener.NewsItemClickListener
import com.kc.newsapp.models.Article
import com.kc.newsapp.repository.NewsRepository
import com.kc.newsapp.ui.NewsActivity
import com.kc.newsapp.ui.viewmodel.NewsViewModel
import com.kc.newsapp.ui.viewmodel.NewsViewModelFactory
import com.kc.newsapp.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakingNewsFragment: Fragment(R.layout.fragment_breaking_news) {

    private lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    val TAG =  "BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Creates a problem that onViewCreated is called before host activity's onCreate
        // viewModel = (activity as NewsActivity).viewModel

        // Below will still reuse viewmodel
        val newsRepository = NewsRepository(ArticleDatabase.getDatabase(requireContext()))
        val viewModelFactory = NewsViewModelFactory(newsRepository)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(NewsViewModel::class.java)
        viewModel.getBreakingNews("us")

        setUpRecyclerView()

        val itemClickListener = object : NewsItemClickListener {
            override fun onItemClicked(article: Article) {
                val bundle = Bundle().apply {
                    putSerializable("article", article)
                }
                findNavController().navigate(R.id.action_breakingNewsFragment_to_articleFragment, bundle)
            }
        }

        newsAdapter.setOnClickListener(itemClickListener)

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideLoading()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideLoading()
                    response.message?.let { message ->
                        Log.d(TAG, "Error occurred : $message")
                    }
                }
                is Resource.Loading -> {
                    showLoading()
                }
            }
        })
    }

    private fun showLoading() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        paginationProgressBar.visibility = View.GONE
    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}