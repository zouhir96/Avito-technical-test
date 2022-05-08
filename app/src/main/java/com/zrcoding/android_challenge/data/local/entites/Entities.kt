package com.zrcoding.android_challenge.data.local.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val overview: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "backdrop_path") val backdropImagePath: String,
    @ColumnInfo(name = "poster_path") val posterImagePath: String,
    val popularity: Double,
    @ColumnInfo(name = "vote_count") val voteCount: Long,
    @ColumnInfo(name = "vote_average") val voteAverage: Double,
    val adult: Boolean,
    val favorite: Boolean,
    val isSynchronized: Boolean,
)

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    @ColumnInfo(name = "movie_id") val movieId: Long,
    @ColumnInfo(name = "prev_key") val prevKey: Int?,
    @ColumnInfo(name = "next_key") val nextKey: Int?
)