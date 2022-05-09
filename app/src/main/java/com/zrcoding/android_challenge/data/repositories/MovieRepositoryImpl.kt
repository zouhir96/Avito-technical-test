package com.zrcoding.android_challenge.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zrcoding.android_challenge.core.Constants.LOCAL_PAGE_SIZE
import com.zrcoding.android_challenge.data.local.AppDatabase
import com.zrcoding.android_challenge.data.local.entities.MovieEntity
import com.zrcoding.android_challenge.data.local.helpers.OrderBy
import com.zrcoding.android_challenge.data.local.helpers.getMovieQuery
import com.zrcoding.android_challenge.data.local.helpers.todBQuery
import com.zrcoding.android_challenge.data.paging.mediators.MovieRemoteMediator
import com.zrcoding.android_challenge.data.remote.MovieDbApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val movieDbApi: MovieDbApi
) : MovieRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getMovies(query: String, orderBy: OrderBy): Flow<PagingData<MovieEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = LOCAL_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = MovieRemoteMediator(
                query,
                movieDbApi = movieDbApi,
                appDatabase = appDatabase
            ),
            pagingSourceFactory = {
                getMovieQuery(
                    appDatabase = appDatabase,
                    orderBy = orderBy,
                    searchQuery = query.todBQuery()
                )
            }
        ).flow
    }
}