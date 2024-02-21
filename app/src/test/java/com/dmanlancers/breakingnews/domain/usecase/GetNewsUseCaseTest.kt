package com.dmanlancers.breakingnews.domain.usecase

import com.dmanlancers.breakingnews.core.Resource
import com.dmanlancers.breakingnews.domain.NewsRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetNewsUseCaseTest {

    private val mockWebServer = MockWebServer()

    @Mock
    private lateinit var newsRepository: NewsRepository

    private lateinit var getNewsUseCase: GetNewsUseCase

    @Before
    fun setUp() {
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetch news successfully returns articles`() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("Your JSON response here")
        mockWebServer.enqueue(mockResponse)


        `when`(newsRepository.getNews()).thenReturn(listOf())

        // C
        val result = getNewsUseCase().toList()


        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
    }


}
