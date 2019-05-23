package com.nathansdev.newsfeed.rxevent

import android.util.Pair
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Event bus for communication between activities and fragment.
 */
class RxEventBus @Inject constructor() {
    private val eventSubject = PublishSubject.create<Pair<String, Any>>()

    /**
     * Return event subject which has a super class as observable.
     *
     * @return Observable.
     */
    fun toObservables(): Observable<Pair<String, Any>> {
        return eventSubject
    }

    /**
     * Check whether subject has observers.
     *
     * @return true or false.
     */
    fun hasObservers(): Boolean {
        return eventSubject.hasObservers()
    }

    /**
     * Send event via subject to the class which subscribed it.
     *
     * @param event event.
     */
    fun send(event: Pair<String, Any>) {
        return eventSubject.onNext(event)
    }
}