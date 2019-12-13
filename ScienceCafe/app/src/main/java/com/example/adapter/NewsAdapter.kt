package com.example.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.example.models.News
import com.example.sciencecafe.R
import com.example.utils.convertLongToDateString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item_view.view.*




class NewsAdapter(val items : List<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.news_item_view, parent, false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news: News = items[position]
        holder?.newsTitleV?.text = news.title
        holder?.newsDateV?.text = convertLongToDateString(news.postedDate)
        Picasso.get().load(news.imageUrl).error(R.drawable.default_news).into(holder?.newsImageV)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(holder.itemView, position)
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsTitleV: TextView = itemView.title
        val newsDateV: TextView = itemView.news_date_view
        val newsImageV : ImageView = itemView.news_image
    }

    class NewsListener(val clickListener: (newsId: Int) -> Unit) {
        fun onClick(news:News) = clickListener(news.id)
    }

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }
}

