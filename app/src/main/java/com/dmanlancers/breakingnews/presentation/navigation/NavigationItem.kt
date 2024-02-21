package com.dmanlancers.breakingnews.presentation.navigation

import com.dmanlancers.breakingnews.core.AppConstants.NEWS_DETAIL_SCREEN_NAVIGATION_ROUTE_ARGUMENTS
import com.dmanlancers.breakingnews.core.AppConstants.NEWS_LIST_SCREEN_NAVIGATION_ROUTE

sealed class NavigationItem(val route: String) {
    data object ListNavigationItem : NavigationItem(NEWS_LIST_SCREEN_NAVIGATION_ROUTE)
    data object DetailScreenNavigationItem
        : NavigationItem(NEWS_DETAIL_SCREEN_NAVIGATION_ROUTE_ARGUMENTS)
}
