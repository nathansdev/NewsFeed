package com.nathansdev.newsfeed.intro

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.nathansdev.newsfeed.R
import com.nathansdev.newsfeed.base.BaseActivity
import com.nathansdev.newsfeed.home.HomeActivity
import com.nathansdev.newsfeed.login.LoginActivity
import com.nathansdev.newsfeed.storage.AppPreferences
import kotlinx.android.synthetic.main.activity_intro.*
import javax.inject.Inject


/**
 *  Intro screen base on slider theme.
 */
class IntroActivity : BaseActivity(), ViewPager.OnPageChangeListener {

    @Inject
    lateinit var appPreferences: AppPreferences
    private lateinit var introPagerAdapter: IntroPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        setContentView(R.layout.activity_intro)
        setUpViews()
    }

    private fun setUpViews() {
        btn_next.setOnClickListener { onNextClicked() }
        btn_skip.setOnClickListener { onSkipClicked() }
        introPagerAdapter = IntroPagerAdapter(this)
        view_pager.adapter = introPagerAdapter
        view_pager.addOnPageChangeListener(this)
        changeCurrentViewDots(0)
        changeStatusBarBackground()
    }

    private fun onNextClicked() {
        val current = getCurrentItem(+1)
        if (current < introPagerAdapter.count) {
            view_pager.currentItem = current
        } else {
            routeTo()
        }
    }

    private fun onSkipClicked() {
        routeTo()
    }

    /*
    * routes to the respective page
     */
    private fun routeTo() {
        appPreferences.setIsFirstTimeLaunch(false)
        if (appPreferences.isLoggedIn()) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        changeCurrentViewDots(position)

        if (position == introPagerAdapter.count - 1) {
            btn_next.text = getString(R.string.start)
            btn_skip.visibility = View.GONE
        } else {
            btn_next.text = getString(R.string.next)
            btn_skip.visibility = View.VISIBLE
        }
    }

    private fun getCurrentItem(i: Int): Int {
        return view_pager.currentItem + i
    }

    private fun changeCurrentViewDots(position: Int) {
        val colorsActive = resources.getIntArray(R.array.array_dot_active)
        val colorsInactive = resources.getIntArray(R.array.array_dot_inactive)

        layout_dots.removeAllViews()

        for (i in 0 until introPagerAdapter.count) {
            val view = TextView(this)
            view.setText(Html.fromHtml("&#8226;"))
            view.setTextSize(35F)
            if (i == position) {
                view.setTextColor(colorsActive[position])
            } else {
                view.setTextColor(colorsInactive[position])
            }
            layout_dots.addView(view)
        }
    }

    private fun changeStatusBarBackground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }
}