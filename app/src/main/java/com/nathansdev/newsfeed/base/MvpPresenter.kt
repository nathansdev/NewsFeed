package com.nathansdev.newsfeed.base

/**
 * Every presenter in app should implement this interface or extend BasePresenter with mvp view type.
 */
interface MvpPresenter<V : MvpView> {
    fun onAttach(mvpView: V)

    fun onDetach()
}