package com.nathansdev.newsfeed.intro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.nathansdev.newsfeed.R


class IntroPagerAdapter(private val context: Context) : PagerAdapter() {

    private val screens = arrayOf(R.layout.layout_intro_screen_one, R.layout.layout_intro_screen_two,
            R.layout.layout_intro_screen_three)

    override fun getCount(): Int {
        return screens.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(screens[position], container, false) as ViewGroup
        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}