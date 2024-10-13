package com.example.movieapp.ui.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movieapp.core.base.ui.BaseFragment
import com.example.movieapp.core.extensions.load
import com.example.movieapp.core.utils.Constant
import com.example.movieapp.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
  
  override fun inflateViewBinding() = FragmentDetailBinding.inflate(layoutInflater)
  override val viewModel by viewModels<DetailViewModel>()
  private val navArgs by navArgs<DetailFragmentArgs>()
  
  override fun initView() {
    val model = navArgs.model
    val poster = Constant.IMAGE_BASE_URL + model.posterPath
    val backdrop = Constant.IMAGE_BASE_URL + model.backdropPath
    with(binding) {
      tvTitle.text = model.title
      tvDescription.text = model.overview
      tvRating.text = model.voteAverage.toString()
      tvRelease.text = model.releaseData
      ivPoster.load(poster,  optimizePicture = false)
      ivBackdrop.load(backdrop, optimizePicture = false)
    }
  }
}