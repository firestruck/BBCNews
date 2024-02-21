package com.dmanlancers.breakingnews.core

object AppConstants {

    const val HTTP_EXCEPTION = "An unexpected error occurred"
    const val SERVER_ERROR = "Couldn't reach server. Check your internet connection"
    const val NO_DATA_AVAILABLE = "No data available, please try again later."
    const val NEWS_LIST_SCREEN_NAVIGATION_ROUTE = "news_list_screen"
    const val NEWS_DETAIL_SCREEN_NAVIGATION_ROUTE = "news_detail_screen"
    const val NEWS_DETAIL_SCREEN_NAVIGATION_ROUTE_ARGUMENTS =
        "news_detail_screen/{urlToImage}/{title}/{description}/{content}"
    const val TITLE_ARGUMENT = "title"
    const val IMAGE_ARGUMENT = "urlToImage"
    const val DESCRIPTION_ARGUMENT = "description"
    const val CONTENT_ARGUMENT = "content"

}