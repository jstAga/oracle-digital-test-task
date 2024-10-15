package com.example.movieapp.ui.detail

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movieapp.R
import com.example.movieapp.core.base.ui.BaseFragment
import com.example.movieapp.core.extensions.load
import com.example.movieapp.core.extensions.setDrawable
import com.example.movieapp.core.utils.Constant
import com.example.movieapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
  
  override fun inflateViewBinding() = FragmentDetailBinding.inflate(layoutInflater)
  override val viewModel by viewModels<DetailViewModel>()
  private val navArgs by navArgs<DetailFragmentArgs>()
  private val movie by lazy { navArgs.model }
  private var isSaved = false
  
  override fun initView() {
    val model = navArgs.model
    val poster = Constant.IMAGE_BASE_URL + model.posterPath
    val backdrop = Constant.IMAGE_BASE_URL + model.backdropPath
    with(binding) {
      tvTitle.text = model.title
      tvDescription.text = model.overview
      tvRating.text = model.voteAverage.toString()
      tvRelease.text = model.releaseData
      ivPoster.load(poster, optimizePicture = false)
      ivBackdrop.load(backdrop, optimizePicture = false)
    }
  }
  
  override fun initRequest() {
    viewModel.isMovieSaved(navArgs.model.id)
  }
  
  override fun initListeners() {
    binding.icSave.setOnClickListener {
      if (isSaved) {
        viewModel.deleteMovie(navArgs.model)
      } else {
        viewModel.createMovie(navArgs.model)
      }
    }
  }
  
  override fun initSubscribers() {
    viewModel.createMovieState.collectUIState {
      viewModel.isMovieSaved(navArgs.model.id)
      Log.e("aga1", "${movie.title} is saved")
    }
    
    viewModel.deleteMovieState.collectUIState {
      viewModel.isMovieSaved(navArgs.model.id)
      Log.e("aga1", "${movie.title} is deleted")
    }
    
    viewModel.isMovieSavedState.collectUIState { isSaved ->
      this.isSaved = isSaved
      val drawableResource = if (isSaved) {
        R.drawable.ic_save_filled
      } else {
        R.drawable.ic_save
      }
      binding.icSave.setDrawable(requireContext(), drawableResource)
    }
  }
}