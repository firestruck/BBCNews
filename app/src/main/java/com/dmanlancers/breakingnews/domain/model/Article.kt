package com.dmanlancers.breakingnews.domain.model


data class Article(
    val title: String,
    val description: String,
    val content: String,
    val publishedAt: String,
    val source: String,
    val urlToImage: String,
)