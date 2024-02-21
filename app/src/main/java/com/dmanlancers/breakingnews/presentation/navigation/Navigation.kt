package com.dmanlancers.breakingnews.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dmanlancers.breakingnews.core.AppConstants.CONTENT_ARGUMENT
import com.dmanlancers.breakingnews.core.AppConstants.DESCRIPTION_ARGUMENT
import com.dmanlancers.breakingnews.core.AppConstants.IMAGE_ARGUMENT
import com.dmanlancers.breakingnews.core.AppConstants.TITLE_ARGUMENT
import com.dmanlancers.breakingnews.presentation.screens.detail.NewsDetailScreen
import com.dmanlancers.breakingnews.presentation.screens.list.NewsListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationItem.ListNavigationItem.route
    ) {
        composable(NavigationItem.ListNavigationItem.route) {
            NewsListScreen(navController)
        }

        composable(NavigationItem.DetailScreenNavigationItem.route) {
            val image = it.arguments?.getString(IMAGE_ARGUMENT)
            val title = it.arguments?.getString(TITLE_ARGUMENT)
            val description = it.arguments?.getString(DESCRIPTION_ARGUMENT)
            val content = it.arguments?.getString(CONTENT_ARGUMENT)

            NewsDetailScreen(
                image.toString(),
                title.toString(),
                description.toString(),
                content.toString()
                )
        }
    }
}