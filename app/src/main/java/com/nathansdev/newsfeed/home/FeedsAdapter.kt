package com.nathansdev.newsfeed.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nathansdev.newsfeed.R
import com.nathansdev.newsfeed.data.Feed

class FeedsAdapter : RecyclerView.Adapter<FeedsViewHolder>() {

    var items: ArrayList<Feed>

    init {
        items = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FeedsViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FeedsViewHolder, position: Int) {
        val movie: Feed = items[position]
        holder.bind(movie)
    }

    fun setData(data: ArrayList<Feed>) {
        items = data
    }

    fun handleDestroy(){
        items.clear()
    }
}

class FeedsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.adapter_item_news_feed, parent, false)) {
    private var feedTitle: TextView? = null
    private var feedDesc: TextView? = null

    init {
        feedTitle = itemView.findViewById(R.id.text_view_feed_title)
        feedDesc = itemView.findViewById(R.id.text_view_feed_desc)
    }

    fun bind(feed: Feed) {
        feedTitle?.text = feed.title
        feedDesc?.text = feed.description
    }
}