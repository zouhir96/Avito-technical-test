package com.zrcoding.android_challenge.ui.movie.details.viewmodels

import androidx.lifecycle.ViewModel
import com.zrcoding.android_challenge.data.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel()