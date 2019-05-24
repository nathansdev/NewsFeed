package com.nathansdev.newsfeed.home

import com.nathansdev.newsfeed.base.MvpView
import com.nathansdev.newsfeed.data.Feed

/**
 * interface between implementer class and view attached to it
 */
interface FeedsView : MvpView {
    fun onNewsLoaded(feeds: ArrayList<Feed>)
}