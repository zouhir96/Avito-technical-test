package com.zrcoding.android_challenge.ui.movie.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zrcoding.android_challenge.R
import com.zrcoding.android_challenge.core.MOVIE_ENTITY_COMPARATOR
import com.zrcoding.android_challenge.databinding.MovieItemBinding

class MovieListAdapter :
    PagingDataAdapter<MovieUiModel, MovieListAdapter.MovieListViewHolder>(MOVIE_ENTITY_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.movie_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieListViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieUiModel?) {
            movie?.let {
                binding.movie = it
            }
        }
    }
}