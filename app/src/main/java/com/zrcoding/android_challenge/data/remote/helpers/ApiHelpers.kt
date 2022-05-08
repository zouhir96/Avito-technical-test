package com.zrcoding.android_challenge.data.remote.helpers

import com.zrcoding.android_challenge.core.Constants.BASE_QUERY_MAP

fun createMovieRequestUrl(searchQuery: String) : String {
    return if (searchQuery.isEmpty()) "movie/popular" else "search/movie"
}

fun createMovieRequestQueryMap(searchQuery: String, page: Int) : Map<String, String> {
    val map = BASE_QUERY_MAP as MutableMap<String, String>
    map["page"] = String().plus(page)
    if (searchQuery.isNotEmpty()) {
        map["query"] = searchQuery
    }
    return map
}