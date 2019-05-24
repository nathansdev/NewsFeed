package com.nathansdev.newsfeed

import timber.log.Timber

/**
 * Customized timber library
 */
class TimberThreadDebugTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val currentThread = Thread.currentThread().name
        if (currentThread.isEmpty()) {
            super.log(priority, tag, message, t)
        } else {
            super.log(priority, tag, StringBuffer(currentThread).append("\n").append(message).toString(), t)
        }
    }
}