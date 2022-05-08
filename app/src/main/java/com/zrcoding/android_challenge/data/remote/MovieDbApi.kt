package com.zrcoding.android_challenge.data.remote

import com.zrcoding.android_challenge.data.remote.dtos.MediaDto
import com.zrcoding.android_challenge.data.remote.dtos.MovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface MovieDbApi {
    @GET
    suspend fun fetchMovies(
        @Url url: String,
        @QueryMap map: Map<String, Any>
    ): Response<MediaDto<MovieDto>>
}