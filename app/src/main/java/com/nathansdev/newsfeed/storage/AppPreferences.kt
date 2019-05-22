package com.nathansdev.newsfeed.storage

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

/**
 * Application preferences backed by shared preference for app.
 */
class AppPreferences(app: Application) {
    private val PREFS_NAME = "app-prefs"
    private val IS_LOGGEDIN = "isLoggedIn"
    private val IS_FIRST_TIME_LAUNCH = "isFirstTimeLaunch"
    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = app.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGGEDIN, false)
    }

    fun setIsLoggedIn(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(IS_LOGGEDIN, isLoggedIn).apply()
    }

    fun isFirstTimeLaunch(): Boolean {
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, true)
    }

    fun setIsFirstTimeLaunch(isFirstTimeLaunch: Boolean) {
        sharedPreferences.edit().putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTimeLaunch).apply()
    }
}