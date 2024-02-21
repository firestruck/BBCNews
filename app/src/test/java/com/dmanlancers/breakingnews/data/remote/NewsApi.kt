import com.dmanlancers.breakingnews.data.remote.NewsApi
import com.dmanlancers.breakingnews.data.remote.model.ArticleDTO
import com.dmanlancers.breakingnews.data.remote.model.NewsDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.any
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class NewsApiTest {

    @Mock
    private lateinit var mockNewsApi: NewsApi
    private lateinit var mockWebServer: MockWebServer
    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mockNewsApi = retrofit.create(NewsApi::class.java)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test getNews returns non-null response`() = runBlockingTest {
        //GIVEN
        val mockResponse = Response.success(
            NewsDTO( listOf(ArticleDTO(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any())
            ),
                any(),
                any()
            )
        )
        //WHEN
        `when`(mockNewsApi.getNews("source_name", "api_key")).thenReturn(mockResponse)

        //THEN
        val response = mockNewsApi.getNews("source_name", "api_key")

        assertNotNull("The response should not be null", response.body())
    }
}
