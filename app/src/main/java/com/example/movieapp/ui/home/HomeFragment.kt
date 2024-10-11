package com.example.movieapp.ui.home

import androidx.fragment.app.viewModels
import com.example.movieapp.core.base.BaseFragment
import com.example.movieapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
  
  override fun inflateViewBinding() = FragmentHomeBinding.inflate(layoutInflater)
  override val viewModel by viewModels<HomeViewModel>()
}