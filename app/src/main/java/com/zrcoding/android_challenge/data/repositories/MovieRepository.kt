package com.zrcoding.android_challenge.data.repositories

import androidx.paging.PagingData
import com.zrcoding.android_challenge.data.local.entities.MovieEntity
import com.zrcoding.android_challenge.data.local.helpers.OrderBy
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(
        query: String = "",
        orderBy: OrderBy = OrderBy.NONE
    ): Flow<PagingData<MovieEntity>>
}