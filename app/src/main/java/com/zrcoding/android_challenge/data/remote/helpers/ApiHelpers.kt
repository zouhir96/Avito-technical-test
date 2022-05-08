package com.zrcoding.android_challenge.data.remote.helpers

import com.zrcoding.android_challenge.core.Constants.BASE_QUERY_MAP

fun createMovieRequestUrl(searchQuery: String) : String {
    return if (searchQuery.isEmpty()) "movie/popular" else "search/movie"
}

fun createMovieRequestQueryMap(searchQuery: String) : Map<String, Any> {
    return if (searchQuery.isEmpty()) BASE_QUERY_MAP else BASE_QUERY_MAP.also {
        it.plus(Pair("query", searchQuery))
    }
}