package com.nathansdev.newsfeed.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nathansdev.newsfeed.R
import com.nathansdev.newsfeed.base.BaseFragment
import com.nathansdev.newsfeed.rxevent.RxEventBus
import kotlinx.android.synthetic.main.fragment_layout_feeds_list.*
import javax.inject.Inject

/**
 * Feeds Fragment with list of news.
 */
class FeedsFragment @Inject constructor() : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var eventBus: RxEventBus
    lateinit var feedAdapter: FeedAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_layout_feeds_list, container, false)
    }

    override fun setUpView(view: View) {
        linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        feedAdapter = FeedAdapter()
        feeds_recycler.apply {
            layoutManager = linearLayoutManager;
            adapter = feedAdapter
        }
    }

    override fun onRefresh() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}