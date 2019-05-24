package com.nathansdev.newsfeed.home

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeActivityModule {

    @ContributesAndroidInjector
    abstract fun provideFeedsFragmentFactory(): FeedsFragment

    @ContributesAndroidInjector
    abstract fun provideDetailedFeedFragmentFactory(): DetailedFeedFragment

    @Binds
    abstract fun provideFeedsViewPresenter(feedViewPresenterImpl: FeedViewPresenterImpl<FeedsView>)
            : FeedsPresenter<FeedsView>
}