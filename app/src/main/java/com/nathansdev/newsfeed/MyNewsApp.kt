package com.nathansdev.newsfeed

import android.app.Activity
import android.app.Application
import android.content.Context
import com.nathansdev.newsfeed.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class MyNewsApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        //initiate dagger component
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
        Timber.plant(TimberThreadDebugTree())
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
}