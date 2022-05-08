package com.zrcoding.android_challenge.core

import com.zrcoding.android_challenge.data.local.entites.MovieEntity
import com.zrcoding.android_challenge.data.remote.dtos.MovieDto

fun List<MovieDto>.toEntities(): List<MovieEntity> {
    return this.map {
        MovieEntity(
            id = it.id,
            title = it.title,
            overview = it.overview?:"",
            releaseDate = it.releaseDate,
            backdropImagePath = it.backdropPath,
            posterImagePath = it.posterPath,
            popularity = it.popularity,
            voteCount = it.voteCount,
            voteAverage = it.voteAverage,
            adult = it.adult,
            favorite = it.favorite,
            isSynchronized = false
        )
    }
}