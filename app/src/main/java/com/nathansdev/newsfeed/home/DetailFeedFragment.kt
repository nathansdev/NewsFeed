package com.nathansdev.newsfeed.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nathansdev.newsfeed.R
import com.nathansdev.newsfeed.base.BaseFragment

class DetailFeedFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_layout_detail_feedview, container, false)
    }

    override fun setUpView(view: View) {

    }
}