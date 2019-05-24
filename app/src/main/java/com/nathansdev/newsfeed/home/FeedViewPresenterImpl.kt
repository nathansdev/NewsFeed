package com.nathansdev.newsfeed.home

import com.nathansdev.newsfeed.base.BasePresenter
import com.nathansdev.newsfeed.data.Feed
import com.nathansdev.newsfeed.data.NewsApi
import com.nathansdev.newsfeed.data.model.Article
import com.nathansdev.newsfeed.data.model.ArticleResponse
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * implementer class for feeds fragment handles all data
 */
class FeedViewPresenterImpl<V : FeedsView> @Inject constructor(val api: NewsApi) : BasePresenter<V>(), FeedsPresenter<V> {
    private val newsSubject = PublishProcessor.create<Long>()
    private val disposables = CompositeDisposable()

    override fun init() {
        val disposable = newsSubject
                .onBackpressureDrop()
                .concatMap { t -> getObservable() }
                .doOnError(Consumer<Throwable> { throwable ->
                    Timber.e(throwable)
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer { t ->
                    (handleOnNewsLoaded(t))
                })
        disposables.add(disposable)
    }

    private fun handleOnNewsLoaded(response: ArticleResponse) {
        val feeds = ArrayList<Feed>()
        if (response.channel.articles.isNotEmpty()) {
            for (article: Article in response.channel.articles)
                feeds.add(Feed(response.channel.title, article.title, article.description, article.link, article.enclosure.url))
        }
        Timber.d("feed loaded %s", feeds.size)
        getMvpView()?.onNewsLoaded(feeds)
    }

    override fun loadNews() {
        Timber.d("load news")
        newsSubject.onNext(0L)
    }

    fun getObservable(): Flowable<ArticleResponse> {
        return api.getNews().subscribeOn(Schedulers.io())
    }
}
