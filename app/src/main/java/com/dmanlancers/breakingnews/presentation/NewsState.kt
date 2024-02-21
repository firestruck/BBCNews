package com.dmanlancers.breakingnews.presentation


import com.dmanlancers.breakingnews.domain.model.Article

data class NewsState(
    val isLoading: Boolean = false,
    val data: List<Article>? = emptyList(),
    val error: String = ""
)
