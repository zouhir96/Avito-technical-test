package com.zrcoding.android_challenge.core

object Constants{
    const val DATABASE_NAME = "movie-db"
    const val API_BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGES_BASE_URL = "https://image.tmdb.org/t/p/"
    val BASE_QUERY_MAP = mapOf(
        "language" to "en-US",
        "include_adult" to String().plus(false)
    )
    const val MDB_STARTING_PAGE_INDEX = 1
    const val LOCAL_PAGE_SIZE = 20
}