package com.dmanlancers.breakingnews.data.repository

import com.dmanlancers.breakingnews.core.ApiRequestResult
import com.dmanlancers.breakingnews.core.toDomain
import com.dmanlancers.breakingnews.data.remote.NewsApi
import com.dmanlancers.breakingnews.domain.NewsRepository
import com.dmanlancers.breakingnews.domain.model.Article
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository, ApiRequestResult() {

    override suspend fun getNews(): List<Article> {
        val response = apiRequestResult { api.getNews() }
        return response.articles?.toDomain() ?: emptyList()
    }
}