package com.example.movieapp.ui.home

import com.example.movieapp.core.base.ui.BaseViewModel
import com.example.movieapp.repository.HomeRepository
import com.example.movieapp.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val homeRepository: HomeRepository,
  private val roomRepository: RoomRepository
) : BaseViewModel() {
  
  private val _fragmentState = MutableStateFlow(HomeFragmentState.ALL_MOVIES)
  val fragmentState: StateFlow<HomeFragmentState> = _fragmentState
  
  fun setFragmentState(state: HomeFragmentState) {
    _fragmentState.value = state
  }
  
  fun getMovies() = homeRepository.getMovies().gatherPagingRequest { it }
  
  fun readMovies() = roomRepository.readMovies().gatherPagingRequest { it }
}