package com.nathansdev.newsfeed.home

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import com.nathansdev.newsfeed.R
import com.nathansdev.newsfeed.base.BaseFragment
import com.nathansdev.newsfeed.data.Feed
import com.nathansdev.newsfeed.rxevent.AppEvents
import com.nathansdev.newsfeed.rxevent.RxEventBus
import com.nathansdev.newsfeed.utils.Utils
import kotlinx.android.synthetic.main.fragment_layout_detail_feedview.*
import kotlinx.android.synthetic.main.layout_screen_mask_with_loader.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Detailed Fragment with full news view.
 */
class DetailedFeedFragment @Inject constructor() : BaseFragment() {
    @Inject
    lateinit var eventBus: RxEventBus

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_layout_detail_feedview, container, false)
    }

    override fun setUpView(view: View) {
        toolbar.navigationIcon = Utils.getTintedVectorAsset(toolbar.context,
                R.drawable.ic_close_black_24dp)
        toolbar.setNavigationOnClickListener { eventBus.send(Pair(AppEvents.BACK_PRESSED, "")) }
        webView_feed.settings.apply {
            setAppCacheEnabled(false)
            cacheMode = WebSettings.LOAD_NO_CACHE
            javaScriptEnabled = true
            loadsImagesAutomatically = true
        }
        screen_mask_with_loader.visibility = View.VISIBLE
        webView_feed.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                screen_mask_with_loader.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                screen_mask_with_loader.visibility = View.GONE
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                Timber.e("error %s", error)
                super.onReceivedError(view, request, error)
            }
        }
    }

    fun handleVisible(feed: Feed) {
        Timber.d("handleVisible %s", feed)
        webView_feed.loadUrl(feed.link)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}