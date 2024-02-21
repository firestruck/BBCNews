package com.dmanlancers.breakingnews.data.remote

import com.dmanlancers.breakingnews.BuildConfig
import com.dmanlancers.breakingnews.data.remote.ApiConstants.URL_PATH
import com.dmanlancers.breakingnews.data.remote.model.NewsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(URL_PATH)
    suspend fun getNews(
        @Query("sources")
        sources: String = BuildConfig.SOURCE_NAME,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): Response<NewsDTO>
}