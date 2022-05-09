package com.zrcoding.android_challenge.data.paging.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.zrcoding.android_challenge.core.Constants.MDB_STARTING_PAGE_INDEX
import com.zrcoding.android_challenge.core.toEntities
import com.zrcoding.android_challenge.data.local.AppDatabase
import com.zrcoding.android_challenge.data.local.entities.MovieEntity
import com.zrcoding.android_challenge.data.local.entities.RemoteKeysEntity
import com.zrcoding.android_challenge.data.remote.MovieDbApi
import com.zrcoding.android_challenge.data.remote.helpers.createMovieRequestQueryMap
import com.zrcoding.android_challenge.data.remote.helpers.createMovieRequestUrl
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val query: String,
    private val appDatabase: AppDatabase,
    private val movieDbApi: MovieDbApi
) : RemoteMediator<Int, MovieEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: MDB_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevPage = remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
                prevPage
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextPage = remoteKeys?.nextKey ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
                nextPage
            }
        }
        try {
            val movieResponse = movieDbApi.fetchMovies(
                createMovieRequestUrl(query),
                createMovieRequestQueryMap(query, page)
            )
            val movies = movieResponse.results
            val endOfPaginationReached = movies.isEmpty()

            val prevKey = if (page == MDB_STARTING_PAGE_INDEX) null else page.minus(1)
            val nextKey = if (endOfPaginationReached) null else page.plus(1)

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDatabase.getMovieDao().clear()
                    appDatabase.getRemoteKeysDao().clear()
                }
                val keys = movies.map {
                    RemoteKeysEntity(movieId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                appDatabase.getRemoteKeysDao().insert(keys)
                appDatabase.getMovieDao().insert(movies.toEntities())
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MovieEntity>): RemoteKeysEntity? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { movie ->
                // Get the remote keys of the last item retrieved
                appDatabase.getRemoteKeysDao().remoteKeysMovieId(movie.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MovieEntity>): RemoteKeysEntity? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movie ->
                // Get the remote keys of the first items retrieved
                appDatabase.getRemoteKeysDao().remoteKeysMovieId(movie.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, MovieEntity>
    ): RemoteKeysEntity? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { movieId ->
                appDatabase.getRemoteKeysDao().remoteKeysMovieId(movieId)
            }
        }
    }
}