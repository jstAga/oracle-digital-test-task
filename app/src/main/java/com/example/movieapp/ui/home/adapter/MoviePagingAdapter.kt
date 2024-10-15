package com.example.movieapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.core.base.data.BaseDiffUtilItemCallback
import com.example.movieapp.core.extensions.load
import com.example.movieapp.core.utils.Constant
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.databinding.ItemFilmBinding
import kotlin.math.ceil

class MoviePagingAdapter(
  private val onClick: (MovieModel) -> Unit
) :
  PagingDataAdapter<MovieModel, MoviePagingAdapter.ViewHolder>(
    BaseDiffUtilItemCallback()
  ) {
  
  override fun onBindViewHolder(
    holder: ViewHolder,
    position: Int
  ) {
    getItem(position)?.let { holder.bind(it) }
  }
  
  override fun onCreateViewHolder(
    parent: ViewGroup, viewType: Int
  ): ViewHolder {
    return ViewHolder(
      ItemFilmBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }
  
  inner class ViewHolder(private val binding: ItemFilmBinding) :
    RecyclerView.ViewHolder(binding.root) {
    
    fun bind(model: MovieModel) = with(binding) {
      val rating = (ceil(model.voteAverage * 10) / 10).toString()
      val poster = Constant.IMAGE_BASE_URL + model.posterPath
      ivPoster.load(poster)
      tvTitle.text = model.title
      tvRating.text = rating
      tvRelease.text = model.releaseData
      tvDescription.text = model.overview
      itemView.setOnClickListener {
        onClick(model)
      }
    }
  }
}