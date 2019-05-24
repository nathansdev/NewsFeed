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
import com.nathansdev.newsfeed.data.Feed
import com.nathansdev.newsfeed.rxevent.RxEventBus
import kotlinx.android.synthetic.main.fragment_layout_feeds_list.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Feeds Fragment with list of news.
 */
class FeedsFragment @Inject constructor() : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, FeedsView {

    @Inject
    lateinit var presenter: FeedsPresenter<FeedsView>
    @Inject
    lateinit var rxEventBus: RxEventBus
    lateinit var feedsAdapter: FeedsAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.onAttach(this)
        return inflater.inflate(R.layout.fragment_layout_feeds_list, container, false)
    }

    override fun setUpView(view: View) {
        Timber.d("setUpView")
        linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        feedsAdapter = FeedsAdapter(rxEventBus)
        feeds_recycler.apply {
            layoutManager = linearLayoutManager;
            adapter = feedsAdapter
        }
        feeds_refresh_layout.setOnRefreshListener(this)
        presenter.init()
        presenter.loadNews()
    }

    override fun onRefresh() {
        Timber.d("onRefresh")
        presenter.loadNews()
    }

    override fun onNewsLoaded(feeds: ArrayList<Feed>) {
        feeds_refresh_layout.isRefreshing = false
        feedsAdapter.setData(feeds)
        feedsAdapter.notifyDataSetChanged()
    }

    override fun onError(message: String) {
        feeds_refresh_layout.isRefreshing = false
    }

    override fun onDestroyView() {
        if (feeds_refresh_layout != null) {
            feeds_refresh_layout.setOnRefreshListener(null)
        }
        feedsAdapter.handleDestroy()
        super.onDestroyView()
    }
}