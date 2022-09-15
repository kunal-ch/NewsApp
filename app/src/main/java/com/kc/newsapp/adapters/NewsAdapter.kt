package com.kc.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kc.newsapp.R
import com.kc.newsapp.listener.NewsItemClickListener
import com.kc.newsapp.models.Article
import kotlinx.android.synthetic.main.item_article_preview.view.*

// notifyDataSetChange is costly
// DiffUtils compares two lists
// Performs operation in background

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
       holder.itemView.apply {
           val article = differ.currentList[position]
           Glide.with(this).load(article.urlToImage).into(ivArticleImage)
           tvSource.text = article.source.name
           tvTitle.text = article.title
           tvDescription.text = article.description
           tvPublishedAt.text = article.publishedAt
       }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var itemClickListener: NewsItemClickListener? = null

    fun setOnClickListener(itemClickListener: NewsItemClickListener) {
        this.itemClickListener = itemClickListener
    }

}