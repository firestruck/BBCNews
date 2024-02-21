package com.dmanlancers.breakingnews.domain

import com.dmanlancers.breakingnews.domain.model.Article


interface NewsRepository {
    suspend fun getNews(): List<Article>
}