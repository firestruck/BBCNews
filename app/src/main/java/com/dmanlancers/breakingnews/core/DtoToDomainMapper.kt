package com.dmanlancers.breakingnews.core

import com.dmanlancers.breakingnews.data.remote.model.ArticleDTO
import com.dmanlancers.breakingnews.domain.model.Article

fun List<ArticleDTO>.toDomain(): List<Article> {
    return mapNotNull {
        Article(
            title = it.title.orEmpty(),
            description = it.description.orEmpty(),
            content = it.content.orEmpty(),
            publishedAt = it.publishedAt.orEmpty(),
            source = it.source?.name.orEmpty(),
            urlToImage = it.urlToImage.orEmpty(),
        )
    }
}
