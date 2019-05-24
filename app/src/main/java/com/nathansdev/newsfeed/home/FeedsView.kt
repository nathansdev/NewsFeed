package com.nathansdev.newsfeed.home

import com.nathansdev.newsfeed.base.MvpView
import com.nathansdev.newsfeed.data.Feed

interface FeedsView : MvpView {
    fun onNewsLoaded(feeds: ArrayList<Feed>)
}