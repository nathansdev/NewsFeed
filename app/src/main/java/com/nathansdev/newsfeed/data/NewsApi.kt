package com.nathansdev.newsfeed.data

import com.nathansdev.newsfeed.data.model.ArticleResponse
import com.nathansdev.newsfeed.rxevent.AppConstants
import io.reactivex.Flowable
import retrofit2.http.GET

interface NewsApi {

    @GET(AppConstants.API_NEWS_XML)
    fun getNews(): Flowable<ArticleResponse>
}
