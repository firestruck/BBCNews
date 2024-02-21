package com.dmanlancers.breakingnews.di


import com.dmanlancers.breakingnews.BuildConfig
import com.dmanlancers.breakingnews.data.remote.NewsApi
import com.dmanlancers.breakingnews.data.repository.NewsRepositoryImpl
import com.dmanlancers.breakingnews.domain.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Singleton
    @Provides
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(NewsApi::class.java)
    }

    @Provides
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepositoryImpl(api = newsApi)
    }
}
