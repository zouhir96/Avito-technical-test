package com.zrcoding.android_challenge.core

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.zrcoding.android_challenge.ui.movie.list.MovieUiModel

val MOVIE_ENTITY_COMPARATOR = object : DiffUtil.ItemCallback<MovieUiModel>() {
    override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem == newItem
    }
}

@BindingAdapter("imagePath")
fun loadImage(imageView: ShapeableImageView, path: String) {
    Glide
        .with(imageView.context)
        .load(path.toImageUrl())
        .into(imageView)
}