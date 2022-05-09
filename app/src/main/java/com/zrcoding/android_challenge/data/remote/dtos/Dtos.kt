package com.zrcoding.android_challenge.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class MediaDto<T>(
    val page:Int,
    val results: List<T>,
    @SerializedName("total_results") val totalResults: Long,
    @SerializedName("total_pages") val totalPages : Long
)

data class MovieDto(
    val id: Long,
    val title: String?,
    @SerializedName("original_title") val originalTitle: String?,
    val overview: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("poster_path") val posterPath: String?,
    val popularity: Double?,
    @SerializedName("vote_count") val voteCount: Long?,
    @SerializedName("vote_average") val voteAverage: Double?,
    val video: Boolean,
    val adult: Boolean,
    val favorite: Boolean,
    @SerializedName("genre_ids") val genreIds: List<Long>
)