package com.backtocoding.composenewsapplication.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmanlancers.breakingnews.core.AppConstants.HTTP_EXCEPTION
import com.dmanlancers.breakingnews.presentation.NewsState
import com.dmanlancers.breakingnews.core.Resource
import com.dmanlancers.breakingnews.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase) :
    ViewModel() {
    val newsState = mutableStateOf(NewsState())

    init {
        getNewsArticles()
    }

    private fun getNewsArticles() {
        getNewsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    newsState.value = NewsState(isLoading = true)
                }
                is Resource.Success -> {
                    newsState.value = NewsState(isLoading = false, result.data  ?: emptyList())
                }
                is Resource.Error -> {
                    newsState.value = NewsState(error = HTTP_EXCEPTION)
                }
            }
        }.launchIn(viewModelScope)
    }
}