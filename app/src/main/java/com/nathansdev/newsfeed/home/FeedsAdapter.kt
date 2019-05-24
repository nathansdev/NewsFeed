package com.nathansdev.newsfeed.home

import android.util.Pair
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nathansdev.newsfeed.R
import com.nathansdev.newsfeed.data.Feed
import com.nathansdev.newsfeed.rxevent.AppEvents
import com.nathansdev.newsfeed.rxevent.RxEventBus
import com.nathansdev.newsfeed.utils.Utils

/**
 * a recyclerview adapter to display list of news
 */
class FeedsAdapter(rxEventBus: RxEventBus) : RecyclerView.Adapter<FeedsViewHolder>() {

    private var items: ArrayList<Feed>
    private val eventBus: RxEventBus

    init {
        items = ArrayList()
        eventBus = rxEventBus
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
        holder.bind(movie, eventBus)
    }

    fun setData(data: ArrayList<Feed>) {
        items = data
    }

    fun handleDestroy(){
        items.clear()
    }
}

/**
 * Adapter view for single row in recyclerview
 */
class FeedsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.adapter_item_news_feed, parent, false)) {
    private var feedTitle: TextView? = null
    private var feedDesc: TextView? = null
    private var feedImage: ImageView? = null

    init {
        feedTitle = itemView.findViewById(R.id.text_view_feed_title)
        feedDesc = itemView.findViewById(R.id.text_view_feed_desc)
        feedImage = itemView.findViewById(R.id.image_view_feed)
    }

    fun bind(feed: Feed, rxEventBus: RxEventBus) {
        feedTitle?.text = feed.title
        feedDesc?.text = feed.description
        itemView.setOnClickListener {
            rxEventBus.send(Pair(AppEvents.FEED_ROW_CLICKED, feed))
        }
        Utils.loadRoundImage(itemView.context, feed.image, feedImage)
    }
}