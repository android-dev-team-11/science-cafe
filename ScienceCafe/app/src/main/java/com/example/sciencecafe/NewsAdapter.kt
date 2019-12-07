package com.example.sciencecafe

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sciencecafe.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_news_detail.view.*
import kotlinx.android.synthetic.main.grid_view_item.view.*
import kotlinx.android.synthetic.main.grid_view_item.view.news_title_view
import kotlinx.android.synthetic.main.grid_view_item.view.author_view
import kotlinx.android.synthetic.main.grid_view_item.view.news_date_view
import kotlinx.android.synthetic.main.grid_view_item.view.news_image


class NewsAdapter(val items : ArrayList<NewsItem>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.grid_view_item, parent, false)
        return ViewHolder(v)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news: NewsItem = items[position]
        holder?.newsTitleV?.text = news.newsTitle
        holder?.newsAuthorV?.text = news.author
        holder?.newsDateV?.text = news.date
        Picasso.get().load("https://i.imgur.com/ZuQxwRe.png").into(holder?.newsImageV)
        holder?.itemView.setOnClickListener{ view: View? ->
            view?.findNavController()?.navigate(R.id.action_newsList_to_newsDetail)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsTitleV: TextView = itemView.news_title_view
        val newsAuthorV: TextView = itemView.author_view
        val newsDateV: TextView = itemView.news_date_view
        val newsImageV : ImageView = itemView.news_image
    }
}

