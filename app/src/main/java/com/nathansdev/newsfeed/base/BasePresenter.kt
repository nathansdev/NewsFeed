package com.nathansdev.newsfeed.base

/**
 * Base class than implements presenter interface and provides base implementation for
 * any view being attached.
 */
open class BasePresenter<V : MvpView> : MvpPresenter<V> {
    private var mMvpView: V? = null

    override fun onAttach(mvpView: V) {
        mMvpView = mvpView
    }

    override fun onDetach() {
        mMvpView = null
    }

    protected fun getMvpView(): V? {
        return mMvpView
    }
}