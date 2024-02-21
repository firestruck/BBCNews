package com.dmanlancers.breakingnews.presentation.screens.list

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.backtocoding.composenewsapplication.presentation.viewmodel.NewsViewModel
import com.dmanlancers.breakingnews.R
import com.dmanlancers.breakingnews.core.AppConstants.NEWS_DETAIL_SCREEN_NAVIGATION_ROUTE
import com.dmanlancers.breakingnews.core.AppConstants.NO_DATA_AVAILABLE
import com.dmanlancers.breakingnews.domain.model.Article


@Composable
fun NewsListScreen(navController: NavController, viewModel: NewsViewModel = hiltViewModel()) {
    val res = viewModel.newsState.value

    if (res.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

    if (res.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = res.error, modifier = Modifier.align(Alignment.Center))
        }
    }

    res.data?.let { articles ->
        LazyColumn {
            items(articles) { article ->
                ArticleItem(article) {
                    val imageUrl = encodeOrDefault(article.urlToImage)
                    val title = encodeOrDefault(article.title)
                    val description = encodeOrDefault(article.description)
                    val content = encodeOrDefault(article.content)

                    navController.navigate("$NEWS_DETAIL_SCREEN_NAVIGATION_ROUTE/$imageUrl/$title/$description/$content")
                }

            }
        }
    }
}

fun encodeOrDefault(text: String?, default: String = NO_DATA_AVAILABLE): String {
    return Uri.encode(text.takeUnless { it.isNullOrEmpty() } ?: default)
}


@Composable
fun ArticleItem(it: Article, onClick: () -> Unit) {
    Column( modifier = Modifier
        .clickable(onClick = onClick)
        .padding(8.dp)
        .shadow(
            elevation = 2.dp,
            shape = RoundedCornerShape(2.dp)
        )
        .border(1.dp, Color.Gray)
        .padding(1.dp)

        ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = it.urlToImage,
                error = painterResource(id = R.drawable.ic_loading_image_error),
            ), contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )

        Text(
            text = it.title,
            style = TextStyle(
                color = Color.Gray,
                fontWeight = FontWeight.Black,
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(12.dp)
        )

    }
}
