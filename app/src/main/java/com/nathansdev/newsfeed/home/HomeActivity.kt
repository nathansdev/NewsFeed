package com.nathansdev.newsfeed.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Pair
import android.view.View
import com.nathansdev.newsfeed.R
import com.nathansdev.newsfeed.base.BaseActivity
import com.nathansdev.newsfeed.data.Feed
import com.nathansdev.newsfeed.login.LoginActivity
import com.nathansdev.newsfeed.rxevent.AppConstants
import com.nathansdev.newsfeed.rxevent.AppEvents
import com.nathansdev.newsfeed.rxevent.RxEventBus
import com.nathansdev.newsfeed.storage.AppPreferences
import com.nathansdev.newsfeed.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_screen_mask_with_loader.*
import timber.log.Timber
import javax.inject.Inject

/**
 *  Home screen displays news feeds
 */
class HomeActivity : BaseActivity() {

    @Inject
    lateinit var rxEventBus: RxEventBus
    @Inject
    lateinit var feedsFragment: FeedsFragment
    @Injectl
    lateinit var detailedFeedFragment: DetailedFeedFragment
    @Inject
    lateinit var appPreferences: AppPreferences

    val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpSubscription()
        setUpViews()
    }

    private fun setUpSubscription() {
        disposables.add(rxEventBus.toObservables()
                .onErrorReturn { throwable ->
                    Timber.e(throwable)
                    null
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { event ->
                    Timber.v("Event received %s", event.first)
                    handleEventData(event)
                }
        )
    }

    private fun handleEventData(event: Pair<String, Any>?) {
        when (event?.first) {
            AppEvents.LOG_OUT_CLICKED -> handleLogOutClicked()
            AppEvents.FEED_ROW_CLICKED -> handleFeedRowClicked(event.second as Feed)
            AppEvents.BACK_PRESSED -> handleBackPressed()
            else -> {
            }
        }
    }

    private fun handleLogOutClicked() {
        screen_mask_with_loader.visibility = View.VISIBLE
        appPreferences.deleteUserNameAndPassword()
        appPreferences.setIsLoggedIn(false)
        Handler().postDelayed(Runnable { routeToLogin() }, 2000)
    }

    private fun handleFeedRowClicked(feed: Feed) {
        Utils.captureTransitionSlide(root)
        feeds_detail_view_container.setVisibility(View.VISIBLE)
        detailedFeedFragment.handleVisible(feed)
    }

    private fun routeToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setUpViews() {
        toolbar.inflateMenu(R.menu.menu_home)
        toolbar.setOnMenuItemClickListener { menuItem ->
            if (menuItem.itemId == R.id.action_logout) {
                rxEventBus.send(Pair(AppEvents.LOG_OUT_CLICKED, ""))
            }
            false
        }
        supportFragmentManager
                .beginTransaction()
                .add(R.id.feeds_container, feedsFragment, AppConstants.FRAG_TAG_FEEDS_LIST)
                .commit()
        supportFragmentManager
                .beginTransaction()
                .add(R.id.feeds_detail_view_container, detailedFeedFragment, AppConstants.FRAG_TAG_FEEDS_DETAIL_VIEW)
                .commit()
        feeds_detail_view_container.visibility = View.INVISIBLE
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

    override fun onBackPressed() {
        handleBackPressed()
    }

    private fun handleBackPressed() {
        if (feeds_detail_view_container.visibility == View.VISIBLE) {
            Utils.captureTransitionSlide(root)
            feeds_detail_view_container.setVisibility(View.INVISIBLE)
        } else {
            super.onBackPressed()
        }
    }

    /**
     * Return Profile fragment by tag.
     *
     * @return Profile fragment.
     */
    private fun getFeedsFrag(): FeedsFragment {
        return supportFragmentManager.findFragmentByTag(AppConstants.FRAG_TAG_FEEDS_LIST) as FeedsFragment
    }
}
