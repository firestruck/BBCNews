import com.backtocoding.composenewsapplication.presentation.viewmodel.NewsViewModel
import com.dmanlancers.breakingnews.core.Resource
import com.dmanlancers.breakingnews.domain.NewsRepository
import com.dmanlancers.breakingnews.domain.model.Article
import com.dmanlancers.breakingnews.domain.usecase.GetNewsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class NewsViewModelTest {

    @Mock
    private lateinit var getNewsUseCase: GetNewsUseCase

    @Mock
    private lateinit var viewModel: NewsViewModel

    @Mock
    private lateinit var repository: NewsRepository

    @Before
    fun setUp() {

        val newsFlow = flow {
            emit(Resource.Loading())
            emit(Resource.Success(listOf(Article(
                "source",
                "author",
                "title",
                "description",
                "url",
                "urlToImage",
            ))))
        }


        `when`(getNewsUseCase.invoke()).thenReturn(newsFlow)


        viewModel = NewsViewModel(getNewsUseCase)
    }

    @Test
    fun `verify state updates when getNewsArticles is called`() = runBlockingTest {

        repository.getNews()


        assert(viewModel.newsState.value.isLoading)
        viewModel.newsState.value.data?.isNotEmpty()?.let { assert(it) }

    }
}
