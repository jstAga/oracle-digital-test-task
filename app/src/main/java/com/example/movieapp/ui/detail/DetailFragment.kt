package com.example.movieapp.ui.detail

import androidx.fragment.app.viewModels
import com.example.movieapp.core.base.ui.BaseFragment
import com.example.movieapp.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
  
  override fun inflateViewBinding() = FragmentDetailBinding.inflate(layoutInflater)
  override val viewModel by viewModels<DetailViewModel>()
}