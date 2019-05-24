package com.nathansdev.newsfeed.home

import com.nathansdev.newsfeed.di.PerFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
/**
 * Home module provides all fragments and implementer instances
 */
abstract class HomeActivityModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun provideFeedsFragmentFactory(): FeedsFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun provideDetailedFeedFragmentFactory(): DetailedFeedFragment

    @Binds
    abstract fun provideFeedsViewPresenter(feedViewPresenterImpl: FeedViewPresenterImpl<FeedsView>)
            : FeedsPresenter<FeedsView>
}