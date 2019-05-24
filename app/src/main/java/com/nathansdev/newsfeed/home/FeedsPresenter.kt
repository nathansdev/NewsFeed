package com.nathansdev.newsfeed.home

import com.nathansdev.newsfeed.base.MvpPresenter
import com.nathansdev.newsfeed.base.MvpView

interface FeedsPresenter<V : MvpView> : MvpPresenter<V> {
    fun init()

    fun loadNews()
}