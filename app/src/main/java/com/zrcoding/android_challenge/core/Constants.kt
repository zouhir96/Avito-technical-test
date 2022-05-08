package com.zrcoding.android_challenge.core

object Constants{
    const val API_KEY = "8d6a13d4ff986513574e73180f498ed9"
    const val API_BASE_URL = "https://www.themoviedb.org/3/"
    const val IMAGES_BASE_URL = "https://image.tmdb.org/t/p/"
    val BASE_QUERY_MAP = mapOf(
        "api_key" to API_KEY,
        "language" to "en-US",
        "include_adult" to false
    )
    const val MDB_STARTING_PAGE_INDEX = 1
    const val PAGE_INDEX_OUT_OF_BOX_ERROR_CODE = 512
    const val NETWORK_PAGE_SIZE = 30
}