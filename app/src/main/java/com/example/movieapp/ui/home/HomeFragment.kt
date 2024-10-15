package com.example.movieapp.ui.home

import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.movieapp.R
import com.example.movieapp.core.base.ui.BaseFragment
import com.example.movieapp.core.extensions.buildNavOptions
import com.example.movieapp.core.extensions.fetchColor
import com.example.movieapp.core.extensions.safeNavigate
import com.example.movieapp.core.extensions.showToast
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.ui.home.adapter.MoviePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
  
  override fun inflateViewBinding() = FragmentHomeBinding.inflate(layoutInflater)
  override val viewModel by viewModels<HomeViewModel>()
  private val movieAdapter by lazy { MoviePagingAdapter(this::onClick) }
  private var fragmentState = HomeFragmentState.ALL_MOVIES
  
  override fun initView() {
    binding.rvFilms.adapter = movieAdapter
  }
  
  override fun initListeners() = with(binding) {
    listenLoadState()
    
    binding.swipeRefreshLayout.setOnRefreshListener {
      fetchMovies(fragmentState)
    }
    
    tvAllMovies.setOnClickListener {
      viewModel.setFragmentState(HomeFragmentState.ALL_MOVIES)
      binding.layoutError.isVisible = false
    }
    
    tvWatchList.setOnClickListener {
      viewModel.setFragmentState(HomeFragmentState.WATCH_LIST)
    }
  }
  
  override fun initSubscribers() {
    viewLifecycleOwner.lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.fragmentState.collect { state ->
          fragmentState = state
          fetchMovies(state)
        }
      }
    }
  }
  
  private fun listenLoadState() {
    movieAdapter.addLoadStateListener { loadState ->
      if (loadState.refresh is LoadState.NotLoading &&
        movieAdapter.itemCount == 0
      ) {
        when (fragmentState) {
          HomeFragmentState.ALL_MOVIES -> {
          }
          
          HomeFragmentState.WATCH_LIST -> {
            binding.layoutError.isVisible = true
          }
        }
      } else if (loadState.refresh is LoadState.Error) {
        showToast(R.string.all_movies_is_empty)
      }
    }
  }
  
  private fun fetchMovies(state: HomeFragmentState) = with(binding) {
    clearPagingData()
    when (state) {
      HomeFragmentState.ALL_MOVIES -> {
        viewModel.getMovies().collectPaging { movieAdapter.submitData(it) }
        setFocus(focused = tvAllMovies, unfocused = tvWatchList)
      }
      
      HomeFragmentState.WATCH_LIST -> {
        viewModel.readMovies().collectPaging { movieAdapter.submitData(it) }
        setFocus(focused = tvWatchList, unfocused = tvAllMovies)
      }
    }
    swipeRefreshLayout.isRefreshing = false
  }
  
  private fun onClick(model: MovieModel) {
    findNavController().safeNavigate(
      HomeFragmentDirections.actionHomeFragmentToDetailFragment(model = model),
      buildNavOptions()
    )
  }
  
  private fun setFocus(focused: TextView, unfocused: TextView) {
    focused.setTextColor(fetchColor(R.color.blue))
    unfocused.setTextColor(fetchColor(R.color.white))
  }
  
  private fun clearPagingData() {
    lifecycleScope.launch {
      movieAdapter.submitData(PagingData.empty())
    }
  }
}