package com.nathansdev.newsfeed.base

/**
 * Base interface that any view class must implement.
 */
interface MvpView {
    fun showMessageInDialog(message: String)

    fun showMessageInToast(message: String)
}