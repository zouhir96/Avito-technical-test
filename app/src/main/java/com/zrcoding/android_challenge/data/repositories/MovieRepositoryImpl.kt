package com.zrcoding.android_challenge.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zrcoding.android_challenge.core.Constants.NETWORK_PAGE_SIZE
import com.zrcoding.android_challenge.data.local.AppDatabase
import com.zrcoding.android_challenge.data.local.entites.MovieEntity
import com.zrcoding.android_challenge.data.local.helpers.OrderBy
import com.zrcoding.android_challenge.data.local.helpers.getMovieQuery
import com.zrcoding.android_challenge.data.remote.MovieDbApi
import com.zrcoding.android_challenge.data.remote.mediators.MovieRemoteMediator
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val movieDbApi: MovieDbApi
) : MovieRepository {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getMovies(query: String, orderBy: OrderBy): Flow<PagingData<MovieEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
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
                    orderBy = OrderBy.NONE,
                    searchQuery = query
                )
            }
        ).flow
    }
}