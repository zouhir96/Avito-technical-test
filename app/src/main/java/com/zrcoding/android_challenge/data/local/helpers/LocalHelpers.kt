package com.zrcoding.android_challenge.data.local.helpers

import androidx.paging.PagingSource
import com.zrcoding.android_challenge.data.local.AppDatabase
import com.zrcoding.android_challenge.data.local.entities.MovieEntity

enum class OrderBy{
    NONE, DATE_ASC, DATE_DESC, ALPHABETICAL_ASC, ALPHABETICAL_DESC
}

fun getMovieQuery(
    appDatabase: AppDatabase,
    searchQuery: String,
    orderBy: OrderBy
): PagingSource<Int, MovieEntity> {
    return if (searchQuery.isEmpty()) when(orderBy) {
        OrderBy.NONE -> appDatabase.getMovieDao().getAll()
        OrderBy.DATE_ASC -> appDatabase.getMovieDao().getAllOrderedByDateAsc()
        OrderBy.DATE_DESC -> appDatabase.getMovieDao().getAllOrderedByDateDesc()
        OrderBy.ALPHABETICAL_ASC -> appDatabase.getMovieDao().getAllOrderedByNameAsc()
        OrderBy.ALPHABETICAL_DESC -> appDatabase.getMovieDao().getAllOrderedByNameDesc()
    } else when(orderBy) {
        OrderBy.NONE -> appDatabase.getMovieDao().searchMovies(searchQuery)
        OrderBy.DATE_ASC -> appDatabase.getMovieDao().searchMoviesOrderedByDateAsc(searchQuery)
        OrderBy.DATE_DESC -> appDatabase.getMovieDao().searchMoviesOrderedByDateDesc(searchQuery)
        OrderBy.ALPHABETICAL_ASC -> appDatabase.getMovieDao().searchMoviesOrderedByNameAsc(searchQuery)
        OrderBy.ALPHABETICAL_DESC -> appDatabase.getMovieDao().searchMoviesOrderedByNameDesc(searchQuery)
    }
}

fun String.todBQuery() :String {
    return "%${this.replace(' ', '%')}%"
}