package com.example.movieapp.ui.home

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.core.base.ui.BaseFragment
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.ui.home.adapter.MoviePagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
  
  override fun inflateViewBinding() = FragmentHomeBinding.inflate(layoutInflater)
  override val viewModel by viewModels<HomeViewModel>()
  private val movieAdapter by lazy { MoviePagingAdapter(this::onClick) }
  
  override fun initView() {
    super.initView()
    binding.rvFilms.adapter = movieAdapter
  }
  
  override fun initRequest() {
    viewModel.getFilms().collectPaging { movieAdapter.submitData(it) }
  }
  
  private fun onClick(model: MovieModel) {
    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(model = model))
//    navigateSafely(HomeFragmentDirections.actionHomeFragmentToDetailFragment(model))
  }
}