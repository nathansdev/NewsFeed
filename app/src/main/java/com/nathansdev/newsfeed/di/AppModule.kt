package com.nathansdev.newsfeed.di

import android.app.Application
import com.nathansdev.newsfeed.rxevent.RxEventBus
import com.nathansdev.newsfeed.storage.AppPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
/**
 * Contains all singleton and provides methods needed for app.
 */
class AppModule {

    @Provides
    @Singleton
    fun provideAppPreferences(application: Application): AppPreferences {
        return AppPreferences(application)
    }

    @Provides
    @Singleton
    fun provideRxEventBus(): RxEventBus {
        return RxEventBus()
    }
}