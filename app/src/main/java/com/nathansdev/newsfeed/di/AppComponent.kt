package com.nathansdev.newsfeed.di

import android.app.Application
import com.nathansdev.newsfeed.MyNewsApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    AppModule::class,
    ApiModule::class,
    ActivityBuilderModule::class])
/**
 * Generates all dagger instances for the required objects.
 */
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: MyNewsApp)
}