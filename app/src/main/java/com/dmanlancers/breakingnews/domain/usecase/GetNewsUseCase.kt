package com.dmanlancers.breakingnews.domain.usecase

import com.dmanlancers.breakingnews.core.AppConstants.HTTP_EXCEPTION
import com.dmanlancers.breakingnews.core.AppConstants.SERVER_ERROR
import com.dmanlancers.breakingnews.core.Resource
import com.dmanlancers.breakingnews.domain.NewsRepository
import com.dmanlancers.breakingnews.domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    operator fun invoke(): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try {
            val articles = newsRepository.getNews()
                    .sortedByDescending { it.publishedAt }

            emit(Resource.Success(articles))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: HTTP_EXCEPTION))
        } catch (e: IOException) {
            emit(Resource.Error(SERVER_ERROR))
        }
    }
}