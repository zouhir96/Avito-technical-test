package com.zrcoding.android_challenge.ui.movie.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.zrcoding.android_challenge.core.toMovieUiModel
import com.zrcoding.android_challenge.data.local.helpers.OrderBy
import com.zrcoding.android_challenge.data.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    var pagingDataFlow: Flow<PagingData<MovieUiModel>>

    val accept: (MovieUiActions) -> Unit

    init {
        val uiActionsStateFlow = MutableSharedFlow<MovieUiActions>()
        val search = uiActionsStateFlow
            .filterIsInstance<MovieUiActions.Search>()
            .distinctUntilChanged()
            .onStart { emit(MovieUiActions.Search()) }

        val sort = uiActionsStateFlow
            .filterIsInstance<MovieUiActions.Sort>()
            .distinctUntilChanged()
            .onStart { emit(MovieUiActions.Sort()) }

        pagingDataFlow = combine(search, sort, ::Pair).flatMapLatest {
            movieRepository
                .getMovies(query = it.first.text, orderBy = it.second.orderBy)
                .map { pagingData ->
                    pagingData.map {
                        it.toMovieUiModel()
                    }
                }
        }.cachedIn(viewModelScope)

        accept = { action ->
            viewModelScope.launch { uiActionsStateFlow.emit(action) }
        }
    }
}

sealed class MovieUiActions {
    class Search(val text: String = "") : MovieUiActions()
    class Sort(val orderBy: OrderBy = OrderBy.DATE_DESC) : MovieUiActions()
}