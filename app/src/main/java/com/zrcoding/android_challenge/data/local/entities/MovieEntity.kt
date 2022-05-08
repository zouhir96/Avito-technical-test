package com.zrcoding.android_challenge.data.local.entities

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