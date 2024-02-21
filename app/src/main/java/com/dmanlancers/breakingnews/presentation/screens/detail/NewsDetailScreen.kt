package com.dmanlancers.breakingnews.presentation.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.backtocoding.composenewsapplication.presentation.viewmodel.NewsViewModel
import com.dmanlancers.breakingnews.R

@Composable
fun NewsDetailScreen(
    image: String,
    title: String,
    description: String,
    content: String,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val res = viewModel.newsState.value

    // Use a Scrollable Column
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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

        Image(
            painter = rememberAsyncImagePainter(
                model = image,
                error = painterResource(id = R.drawable.ic_loading_image_error), // Add an error placeholder
                placeholder = painterResource(id = R.drawable.ic_image_placeholder) // Add a loading placeholder
            ),
            contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = title,
            style = TextStyle(
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(12.dp)
        )

        Text(
            text = description,
            style = TextStyle(
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(12.dp)
        )

        Text(
            text = content,
            style = TextStyle(
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(12.dp)
        )
    }
}
