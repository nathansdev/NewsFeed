package com.nathansdev.newsfeed.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.nathansdev.newsfeed.R
import com.nathansdev.newsfeed.base.BaseActivity
import com.nathansdev.newsfeed.home.HomeActivity
import com.nathansdev.newsfeed.intro.IntroActivity
import com.nathansdev.newsfeed.login.LoginActivity
import com.nathansdev.newsfeed.storage.AppPreferences
import javax.inject.Inject

/**
 *  Splash screen with launcher theme.
 */
class SplashActivity : BaseActivity() {
    @Inject
    lateinit var appPreferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Launcher)
        super.onCreate(savedInstanceState)
    }

    /**
     * launches specific activities base on the conditions
     */
    fun routeTo() {
        Handler().postDelayed({
            if (appPreferences.isLoggedIn()) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                if (appPreferences.isFirstTimeLaunch()) {
                    val intent = Intent(this, IntroActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }, 1000)
    }
}