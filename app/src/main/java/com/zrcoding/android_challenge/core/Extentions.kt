package com.zrcoding.android_challenge.core

import com.zrcoding.android_challenge.data.local.entities.MovieEntity
import com.zrcoding.android_challenge.data.remote.dtos.MovieDto
import com.zrcoding.android_challenge.ui.movie.list.MovieUiModel

fun List<MovieDto>.toEntities(): List<MovieEntity> {
    return this.map {
        MovieEntity(
            id = it.id,
            title = it.title?:"",
            overview = it.overview?:"",
            releaseDate = it.releaseDate?:"",
            backdropImagePath = it.backdropPath?:"",
            posterImagePath = it.posterPath?:"",
            popularity = it.popularity?:0.0,
            voteCount = it.voteCount?:0,
            voteAverage = it.voteAverage?:0.0,
            adult = it.adult,
            favorite = it.favorite,
            isSynchronized = false
        )
    }
}

fun MovieEntity.toMovieUiModel(): MovieUiModel{
    return MovieUiModel(
        id = this.id,
        title = this.title,
        date = this.releaseDate,
        imagePath = this.backdropImagePath,
        votes = this.voteCount,
        rate = this.voteAverage,
    )
}

fun String.toImageUrl(): String {
    return "${Constants.IMAGES_BASE_URL}/w500$this"
}