package com.zrcoding.android_challenge.ui.movie.list

data class MovieUiModel(
    val id: Long,
    val title: String,
    val date: String,
    val imagePath: String,
    val rate: Double,
    val votes: Long
)