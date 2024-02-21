package com.dmanlancers.breakingnews.data.remote.model


data class NewsDTO(
    val articles: List<ArticleDTO>?,
    val status: String,
    val totalResults: Int
)