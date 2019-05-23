package com.nathansdev.newsfeed.di

import com.nathansdev.newsfeed.home.HomeActivity
import com.nathansdev.newsfeed.intro.IntroActivity
import com.nathansdev.newsfeed.login.LoginActivity
import com.nathansdev.newsfeed.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Activity builder class that maps all activities in graph using dagger.
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector()
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector()
    abstract fun bindIntroActivity(): IntroActivity

    @ContributesAndroidInjector()
    abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector()
    abstract fun bindLoginActivity(): LoginActivity
}